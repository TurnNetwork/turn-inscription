package com.turn.inscription.bootstrap.service;

import com.alibaba.fastjson.JSON;
import com.turn.inscription.bean.*;
import com.turn.inscription.bootstrap.bean.InitializationResult;
import com.turn.inscription.cache.*;
import com.turn.inscription.config.BlockChainConfig;
import com.turn.inscription.dao.entity.*;
import com.turn.inscription.dao.mapper.*;
import com.turn.inscription.enums.AddressTypeEnum;
import com.turn.inscription.exception.BlockNumberException;
import com.turn.inscription.exception.BusinessException;
import com.turn.inscription.publisher.GasEstimateEventPublisher;
import com.turn.inscription.service.elasticsearch.*;
import com.turn.inscription.service.epoch.EpochRetryService;
import com.turn.inscription.service.govern.ParameterService;
import com.turn.inscription.service.ppos.StakeEpochService;
import com.turn.inscription.utils.EpochUtil;
import com.turn.inscription.v0152.analyzer.ErcCache;
import com.bubble.contracts.dpos.dto.resp.Node;
import com.turn.inscription.v0152.analyzer.GameCache;
import com.turn.inscription.v0152.analyzer.InscriptionCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @description: Start initialization service
 **/
@Slf4j
@Service
public class InitializationService {

    private static final InitializationResult initialResult = new InitializationResult();

    @Resource
    private EpochRetryService epochRetryService;

    @Resource
    private BlockChainConfig chainConfig;

    @Resource
    private NodeMapper nodeMapper;

    @Resource
    private StakingMapper stakingMapper;

    @Resource
    private NetworkStatMapper networkStatMapper;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private NodeCache nodeCache;

    @Resource
    private NetworkStatCache networkStatCache;

    @Resource
    private AddressCache addressCache;

    @Resource
    private ParameterService parameterService;

    @Resource
    private ProposalCache proposalCache;

    @Resource
    private GasEstimateLogMapper gasEstimateLogMapper;

    @Resource
    private GasEstimateEventPublisher gasEstimateEventPublisher;

    @Resource
    private StakeEpochService stakeEpochService;

    @Resource
    private EsBlockRepository esBlockRepository;

    @Resource
    private EsTransactionRepository esTransactionRepository;

    @Resource
    private EsDelegationRewardRepository esDelegationRewardRepository;

    @Resource
    private EsNodeOptRepository esNodeOptRepository;

    @Resource
    private EsErc20TxRepository esErc20TxRepository;

    @Resource
    private EsErc721TxRepository esErc721TxRepository;

    @Resource
    private EsErc1155TxRepository esErc1155TxRepository;

    @Resource
    private EsTransferTxRepository esTransferTxRepository;

    @Resource
    private ErcCache ercCache;

    @Resource
    private EsMicroNodeOptRepository esMicroNodeOptRepository;

    @Resource
    private GameCache gameCache;

    @Resource
    private InscriptionCache inscriptionCache;

    @Resource
    private EsInscriptionTxRepository esInscriptionTxRepository;

    /**
     * Enter the application initialization sub-process
     */
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public InitializationResult init(String traceId) throws BlockNumberException {
        log.info("Enter the application initialization sub-process");
        proposalCache.init();
        ercCache.init();
        //Initialize ES
        initEs();
        // Check the database network_stat table, if there is no record, add one, and query the latest built-in validator node from the chain and store it in the staking table and node table
        NetworkStat networkStat = networkStatMapper.selectByPrimaryKey(1);
        if (networkStat == null) {
            // Make sure chainConfig is ready first
            try {
                parameterService.initConfigTable();
            } catch (Exception e) {
                log.error("", e);
                throw new BusinessException("Initialization error:" + e.getMessage());
            }
            //Create new statistics record
            networkStat = CollectionNetworkStat.newInstance();
            networkStat.setId(1);
            networkStat.setCurNumber(-1L);
            networkStat.setAvgPackTime(0L);
            networkStat.setIssueRates(chainConfig.getAddIssueRate().toPlainString());
            networkStat.setErc20TxQty(0);
            networkStat.setErc721TxQty(0);
            networkStat.setErc1155TxQty(0);
            networkStatMapper.insert(networkStat);
            initialResult.setCollectedBlockNumber(-1L);
            // Delete the node table, pledge table, and address table data
            nodeMapper.deleteByExample(null);
            stakingMapper.deleteByExample(null);
            addressMapper.deleteByExample(null);
            log.info("Delete node table, pledge table, address table data");
            //Initialize the built-in node
            List<com.turn.inscription.dao.entity.Node> nodeList = initInnerStake();
            //Initialize node cache
            nodeCache.init(nodeList);
            //Initialize network cache
            networkStatCache.init(networkStat);
            //Initialize the built-in address
            addressCache.initOnFirstStart();
            return initialResult;
        }

        // Make sure chainConfig is ready first
        parameterService.overrideBlockChainConfig();

        initialResult.setCollectedBlockNumber(networkStat.getCurNumber());

        //Initialize node cache
        List<com.turn.inscription.dao.entity.Node> nodeList = nodeMapper.selectByExample(null);
        nodeCache.init(nodeList);

        // Initialize the EVM contract address cache for type judgment of subsequent transactions (calling the EVM contract)
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andTypeEqualTo(AddressTypeEnum.EVM_CONTRACT.getCode());
        List<Address> addressList = addressMapper.selectByExample(addressExample);
        addressCache.initEvmContractAddressCache(addressList);

        // Initialize the EVM contract address cache for type judgment of subsequent transactions (calling the EVM contract)
        addressExample = new AddressExample();
        addressExample.createCriteria().andTypeEqualTo(AddressTypeEnum.ERC20_EVM_CONTRACT.getCode());
        addressList = addressMapper.selectByExample(addressExample);

        // Initialize the WASM contract address cache, which is used to determine the type of subsequent transactions (calling the WASM contract)
        addressExample = new AddressExample();
        addressExample.createCriteria().andTypeEqualTo(AddressTypeEnum.WASM_CONTRACT.getCode());
        addressList = addressMapper.selectByExample(addressExample);
        addressCache.initWasmContractAddressCache(addressList);

        //Initialize the game contract
        gameCache.init();

        //Initialize the inscription contract
        inscriptionCache.init();

        //Initialize network cache
        networkStatCache.init(networkStat);

        // Ensure that various attributes in epochRetryService are in the current block state: current consensus and settlement cycle validators, previous consensus and settlement cycle validators, etc.
        epochRetryService.issueChange(BigInteger.valueOf(networkStat.getCurNumber()));
        epochRetryService.settlementChange(BigInteger.valueOf(networkStat.getCurNumber()));
        epochRetryService.consensusChange(BigInteger.valueOf(networkStat.getCurNumber()));

        // Check the gas price estimation data table
        GasEstimateLogExample condition = new GasEstimateLogExample();
        condition.setOrderByClause("seq asc");
        List<GasEstimateLog> gasEstimateLogs = gasEstimateLogMapper.selectByExample(condition);
        gasEstimateLogs.forEach(e -> {
            List<GasEstimate> estimates = JSON.parseArray(e.getJson(), GasEstimate.class);
            if (estimates != null && !estimates.isEmpty()) {
                gasEstimateEventPublisher.publish(e.getSeq(), estimates, traceId);
            }
        });

        return initialResult;
    }

    /**
     * Initialize the warehousing internal pledge node
     *
     * @throws Exception
     */
    private List<com.turn.inscription.dao.entity.Node> initInnerStake() throws BlockNumberException {
        log.info("Initialize built-in nodes");
        epochRetryService.issueChange(BigInteger.ZERO);
        epochRetryService.settlementChange(BigInteger.ZERO);
        epochRetryService.consensusChange(BigInteger.ZERO);

        List<CustomNode> nodes = new ArrayList<>();
        List<CustomStaking> stakingList = new ArrayList<>();

        List<Node> validators = epochRetryService.getPreValidators();
        Set<String> validatorSet = new HashSet<>();
        validators.forEach(v -> validatorSet.add(v.getNodeId()));

        // Default built-in node information in configuration
        Map<String, CustomStaking> defaultStakingMap = new HashMap<>();
        chainConfig.getDefaultStakingList().forEach(staking -> defaultStakingMap.put(staking.getNodeId(), staking));

        List<Node> nodeList = epochRetryService.getPreVerifiers();
        for (int index = 0; index < nodeList.size(); index++) {
            Node v = nodeList.get(index);
            CustomStaking staking = new CustomStaking();
            staking.updateWithVerifier(v);
            staking.setStakingTxIndex(index);
            // Set the number of verification rounds in advance
            staking.setStakingReductionEpoch(BigInteger.ONE.intValue());
            staking.setStatus(CustomStaking.StatusEnum.CANDIDATE.getCode());
            staking.setIsInit(CustomStaking.YesNoEnum.YES.getCode());
            staking.setIsSettle(CustomStaking.YesNoEnum.YES.getCode());
            staking.setStakingLocked(chainConfig.getDefaultStakingLockedAmount());
            // If the current candidate node is in the consensus cycle validator list, identify it as a consensus cycle node
            if (validatorSet.contains(v.getNodeId())) {
                staking.setIsConsensus(CustomStaking.YesNoEnum.YES.getCode());
            }

            if (StringUtils.isBlank(staking.getNodeName())) {
                staking.setNodeName("turn.node." + (index + 1));
            }

            //Update the annualized rate information. Since it is the beginning of the cycle, only the cost is recorded. The income needs to be calculated when the settlement cycle switches.
            AnnualizedRateInfo ari = new AnnualizedRateInfo();
            // |- Cost of staking
            List<PeriodValueElement> stakeCosts = new ArrayList<>();
            stakeCosts.add(new PeriodValueElement().setPeriod(0L).setValue(BigDecimal.ZERO));
            BigDecimal stakeCostVal = staking.getStakingLocked() // Locked pledge
                    .add(staking.getStakingHes()) // Deposit during the hesitation period
                    .add(staking.getStatDelegateHes()) // Commission fee during the hesitation period
                    .add(staking.getStatDelegateLocked()); // Locked commission
            stakeCosts.add(new PeriodValueElement().setPeriod(1L).setValue(stakeCostVal));
            ari.setStakeCost(stakeCosts);
            // |- The cost of the commission
            List<PeriodValueElement> delegateCosts = new ArrayList<>();
            delegateCosts.add(new PeriodValueElement().setPeriod(0L).setValue(BigDecimal.ZERO));
            BigDecimal delegateCostVal = staking.getStatDelegateLocked() // Locked commission
                    .add(staking.getStatDelegateHes()); // Commission fee during the hesitation period
            delegateCosts.add(new PeriodValueElement().setPeriod(1L).setValue(delegateCostVal));
            ari.setDelegateCost(delegateCosts);

            staking.setAnnualizedRateInfo(ari.toJSONString());
            staking.setPredictStakingReward(epochRetryService.getStakeReward());

            staking.setRewardPer(0);
            staking.setNextRewardPer(0); // Commission reward ratio in the next settlement cycle
            staking.setNextRewardPerModEpoch(0); // [Delegation reward ratio in the next settlement period] Modify the settlement period
            staking.setDeleAnnualizedRate(0.0);
            staking.setPreDeleAnnualizedRate(0.0);
            staking.setHaveDeleReward(BigDecimal.ZERO);
            staking.setTotalDeleReward(BigDecimal.ZERO);
            staking.setExceptionStatus(1);

            BigInteger curSettleEpochRound = EpochUtil.getEpoch(BigInteger.ONE, chainConfig.getSettlePeriodBlockCount()); // The settlement cycle number of the current block
            //Update the number of settlement cycles required to release the pledge to the account
            BigInteger unStakeFreezeDuration = stakeEpochService.getUnStakeFreeDuration();
            // Theoretical exit block number, the actual exit block number should be compared with the voting deadline block of the proposal with status in progress, whichever is the largest
            BigInteger unStakeEndBlock = stakeEpochService.getUnStakeEndBlock("", curSettleEpochRound, false);
            staking.setUnStakeFreezeDuration(unStakeFreezeDuration.intValue());
            staking.setUnStakeEndBlock(unStakeEndBlock.longValue());
            staking.setLowRateSlashCount(0);
            staking.setZeroProduceFreezeDuration(0);
            staking.setZeroProduceFreezeEpoch(0);

            // Use the current pledge information to generate node information
            CustomNode node = new CustomNode();
            node.updateWithCustomStaking(staking);
            node.setStakingTxIndex(index);
            node.setTotalValue(staking.getStakingLocked());
            node.setIsRecommend(CustomNode.YesNoEnum.NO.getCode());
            //Set the number of verification rounds in advance
            node.setStatVerifierTime(BigInteger.ONE.intValue());
            // Expected number of blocks = number of blocks in the consensus cycle/actual number of nodes participating in the consensus
            node.setStatExpectBlockQty(epochRetryService.getExpectBlockCount());
            node.setPreTotalDeleReward(BigDecimal.ZERO);

            nodes.add(node);
            stakingList.add(staking);
        }

        // Warehouse
        List<com.turn.inscription.dao.entity.Node> returnData = new ArrayList<>(nodes);
        if (!nodes.isEmpty()) {
            nodeMapper.batchInsert(returnData);
        }
        if (!stakingList.isEmpty()) {
            stakingMapper.batchInsert(new ArrayList<>(stakingList));
        }
        return returnData;
    }

    /**
     * Initialize ES
     */
    private void initEs() {
        log.info("Initialize ES");
        try {
            esBlockRepository.initIndex();
            esTransactionRepository.initIndex();
            esDelegationRewardRepository.initIndex();
            esNodeOptRepository.initIndex();
            esTransferTxRepository.initIndex();
            esErc20TxRepository.initIndex();
            esErc721TxRepository.initIndex();
            esErc1155TxRepository.initIndex();
            esMicroNodeOptRepository.initIndex();
            esInscriptionTxRepository.initIndex();
        } catch (Exception e) {
            log.error("Initialization ES exception", e);
        }

    }

}
