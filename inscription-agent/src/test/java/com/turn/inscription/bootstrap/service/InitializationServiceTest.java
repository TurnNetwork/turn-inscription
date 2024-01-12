package com.turn.inscription.bootstrap.service;

import com.github.pagehelper.Page;
import com.turn.inscription.AgentTestBase;
import com.turn.inscription.bootstrap.bean.InitializationResult;
import com.turn.inscription.bean.CollectionNetworkStat;
import com.turn.inscription.cache.AddressCache;
import com.turn.inscription.cache.NetworkStatCache;
import com.turn.inscription.cache.NodeCache;
import com.turn.inscription.cache.ProposalCache;
import com.turn.inscription.publisher.GasEstimateEventPublisher;
import com.turn.inscription.service.elasticsearch.*;
import com.turn.inscription.service.epoch.EpochRetryService;
import com.turn.inscription.config.BlockChainConfig;
import com.turn.inscription.dao.entity.GasEstimateLog;
import com.turn.inscription.dao.entity.NetworkStat;
import com.turn.inscription.dao.mapper.*;
import com.turn.inscription.bean.CustomStaking;
import com.turn.inscription.service.govern.ParameterService;
import com.turn.inscription.service.ppos.StakeEpochService;
import com.turn.inscription.utils.CommonUtil;
import com.turn.inscription.v0152.analyzer.ErcCache;
import com.bubble.contracts.dpos.dto.resp.Node;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * @description: MySQL/ES/Redis启动一致性自检服务测试
 **/
@RunWith(MockitoJUnitRunner.Silent.class)
public class InitializationServiceTest extends AgentTestBase {

    @Mock
    private EpochRetryService epochRetryService;

    @Mock
    private BlockChainConfig chainConfig;

    @Mock
    private NodeMapper nodeMapper;

    @Mock
    private StakingMapper stakingMapper;

    @Mock
    private NetworkStatMapper networkStatMapper;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private NodeCache nodeCache;

    @Mock
    private NetworkStatCache networkStatCache;

    @Mock
    private AddressCache addressCache;

    @Mock
    private ProposalMapper proposalMapper;

    @Mock
    private ProposalCache proposalCache;

    @Mock
    private ParameterService parameterService;

    @Mock
    private GasEstimateLogMapper gasEstimateLogMapper;

    @Mock
    private GasEstimateEventPublisher gasEstimateEventPublisher;

    @Mock
    private StakeEpochService stakeEpochService;

    @InjectMocks
    @Spy
    private InitializationService target;

    @Mock
    private ErcCache ercCache;

    @Mock
    private TokenMapper tokenMapper;

    @Mock
    private EsBlockRepository ESBlockRepository;

    @Mock
    private EsTransactionRepository ESTransactionRepository;

    @Mock
    private EsDelegationRewardRepository ESDelegationRewardRepository;

    @Mock
    private EsNodeOptRepository ESNodeOptRepository;

    @Mock
    private EsErc20TxRepository esErc20TxRepository;

    @Mock
    private EsErc721TxRepository esErc721TxRepository;

    @Mock
    private EsTransferTxRepository esTransferTxRepository;

    @Before
    public void setup() throws Exception {
        // 修改测试数据
        Node node = candidateList.get(0);
        node.setNodeName("node");
        node.setProgramVersion(BigInteger.valueOf(7988));
        node.setStakingBlockNum(BigInteger.valueOf(7988));
        Node node2 = candidateList.get(1);
        node2.setNodeName(null);
        CustomStaking staking = stakingList.get(0);
        staking.setNodeId(node.getNodeId());
        staking.setNodeName("node-name");

        when(epochRetryService.getPreValidators()).thenReturn(candidateList);
        when(epochRetryService.getPreVerifiers()).thenReturn(candidateList);
        when(epochRetryService.getCandidates()).thenReturn(candidateList);
        when(epochRetryService.getExpectBlockCount()).thenReturn(10L);
        when(chainConfig.getDefaultStakingList()).thenReturn(stakingList);
        when(chainConfig.getDefaultStakingLockedAmount()).thenReturn(BigDecimal.valueOf(100000000));
        when(chainConfig.getSettlePeriodBlockCount()).thenReturn(BigInteger.TEN);
        when(chainConfig.getAddIssueRate()).thenReturn(BigDecimal.TEN);
        when(proposalMapper.selectByExample(any())).thenReturn(new ArrayList<>(proposalList));
        when(parameterService.getValueInBlockChainConfig(any())).thenReturn("5");
        List<GasEstimateLog> gasEstimateLogs = new ArrayList<>();
        GasEstimateLog gel = new GasEstimateLog();
        gel.setSeq(1l);
        gel.setJson("[]");
        gasEstimateLogs.add(gel);
        when(gasEstimateLogMapper.selectByExample(any())).thenReturn(gasEstimateLogs);
        when(stakeEpochService.getUnStakeFreeDuration()).thenReturn(BigInteger.TEN);
        when(stakeEpochService.getUnStakeEndBlock(anyString(), any(BigInteger.class), anyBoolean())).thenReturn(BigInteger.TEN);
    }

    @Test
    public void post() throws Exception {
        NetworkStat networkStat = null;
        when(networkStatMapper.selectByPrimaryKey(anyInt())).thenReturn(networkStat);
        InitializationResult result = target.init(CommonUtil.createTraceId());
        assertEquals(-1L, result.getCollectedBlockNumber().longValue());

        networkStat = CollectionNetworkStat.newInstance();
        networkStat.setCurNumber(7000L);
        when(networkStatMapper.selectByPrimaryKey(anyInt())).thenReturn(networkStat);
        Page<com.turn.inscription.dao.entity.Node> page = new Page<>();
        when(nodeMapper.selectByExample(any())).thenReturn(page);
        result = target.init(CommonUtil.createTraceId());
        assertEquals(7000L, result.getCollectedBlockNumber().longValue());
    }

}
