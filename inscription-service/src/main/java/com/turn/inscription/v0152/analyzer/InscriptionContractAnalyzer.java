package com.turn.inscription.v0152.analyzer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.bubble.crypto.Credentials;
import com.bubble.parameters.NetworkParameters;
import com.bubble.tx.gas.ContractGasProvider;
import com.bubble.tx.gas.GasProvider;
import com.turn.inscription.bean.CollectionTransaction;
import com.turn.inscription.bean.InscriptionHolderCount;
import com.turn.inscription.bean.InscriptionInventoryDetail;
import com.turn.inscription.bean.InscriptionVO;
import com.turn.inscription.config.BlockChainConfig;
import com.turn.inscription.contract.InscriptionContract;
import com.turn.inscription.dao.custommapper.CustomInscriptionHolderMapper;
import com.turn.inscription.dao.custommapper.CustomInscriptionMapper;
import com.turn.inscription.dao.entity.*;
import com.turn.inscription.dao.mapper.*;
import com.turn.inscription.enums.*;
import com.turn.inscription.service.elasticsearch.EsInscriptionTxService;
import com.turn.inscription.utils.CommonUtil;
import com.turn.inscription.v0152.bean.*;
import com.turn.inscription.v0152.service.InscriptionDetectService;
import com.xxl.job.core.context.XxlJobHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Slf4j
@Service
public class InscriptionContractAnalyzer {

    @Resource
    private InscriptionDetectService inscriptionDetectService;

    @Resource
    private InscriptionCache inscriptionCache;

    @Resource
    private InscriptionMapper inscriptionMapper;

    @Resource
    private InscriptionInventoryMapper inscriptionInventoryMapper;

    @Resource
    private TxInscriptionBakMapper txInscriptionBakMapper;

    @Resource
    private CustomInscriptionHolderMapper customInscriptionHolderMapper;

    @Resource
    private CustomInscriptionMapper customInscriptionMapper;

    @Resource
    private EsInscriptionTxService esInscriptionTxService;

    @Resource
    private BlockChainConfig chainConfig;

    public static Credentials CREDENTIALS;

    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(2104836);

    private static final BigInteger GAS_PRICE = BigInteger.valueOf(100000000000L);

    public static final GasProvider GAS_PROVIDER = new ContractGasProvider(GAS_PRICE, GAS_LIMIT);

    @PostConstruct
    public void init() {
        NetworkParameters.init(chainConfig.getChainId());
        CREDENTIALS = Credentials.create("4484092b68df58d639f11d59738983e2b8b81824f3c0c759edd6773f9adadfe7");
    }

    /**
     * Parse the game contract and call it when the contract is created
     *
     * @param contractAddress
     */
    public InscriptionVO resolveInscriptionContract(String contractAddress) {
        InscriptionVO inscriptionVO = new InscriptionVO();
        try {
            inscriptionVO.setTypeEnum(InscriptionTypeEnum.UNKNOWN);
            inscriptionVO.setContractAddress(contractAddress);
            InscriptionContractId contractId = inscriptionDetectService.getContractId(contractAddress);
            BeanUtils.copyProperties(contractId, inscriptionVO);
            if(inscriptionVO.getTypeEnum() == InscriptionTypeEnum.INSCRIPTION){
                Inscription inscription = new Inscription();
                BeanUtils.copyProperties(contractId, inscription);
                inscription.setContractAddress(contractAddress);
                inscription.setCreateTime(new Date());
                inscription.setHolders(0);
                inscription.setTotalTx(0L);
                inscription.setStatus(1);
                inscription.setMinted(0L);
                inscription.setNeedExtendValue(StrUtil.isEmpty(contractId.getExtendValue())?0:1);
                inscription.setDeployTime(new Date(contractId.getDeployTime()));
                inscriptionMapper.insert(inscription);
                inscriptionCache.inscriptionMapCache.put(contractAddress, inscription);
            } else {
                log.warn("The contract address [{}] cannot recognize the type [{}]", inscriptionVO.getContractAddress(), inscriptionVO.getTypeEnum());
            }
        } catch (Exception e) {
            log.error("Game contract creation, parsing exception", e);
        }
        return inscriptionVO;
    }

    /**
     * Parse inscription transactions, called when the contract is called
     *
     * @param tx transaction object
     */
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void resolveTx(CollectionTransaction tx) {
        try {

            if (CollUtil.isEmpty(tx.getInscriptionContractEventInfo())) {
                return;
            }
            Inscription inscription = inscriptionCache.inscriptionMapCache.get(tx.getTo());
            tx.getInscriptionContractEventInfo().forEach(eventInfo -> {
                Map<String,String> map = JSONObject.parseObject(eventInfo, Map.class);
                map.keySet().stream().forEach(x->{
                    if(InscriptionEventTypeEnum.MINT_EVENT.getDesc().equals(x)){
                        //Handle mint events
                        String eventStr = map.get(x);
                        List<JSONObject> mintEventResponses = JSONObject.parseObject(eventStr, List.class);
                        mintHandle(inscription,mintEventResponses,tx);
                    }
                });
            });
            handleInscriptionStatus(inscription);
            log.info("The current inscription transaction [{}] has [{}] events",
                    tx.getHash(),
                    CommonUtil.ofNullable(() -> tx.getInscriptionContractEventInfo().size()).orElse(0));
        } catch (Exception e) {
            log.error(StrUtil.format("Exception in parsing inscription transaction of current transaction [{}]", tx.getHash()), e);
        }
    }

    private void handleInscriptionStatus(Inscription inscription) {
        Inscription inscriptionTemp = inscriptionMapper.selectByPrimaryKey(inscription.getId());
        if(inscriptionTemp.getTotalSupply().equals(inscriptionTemp.getLimitPerMint()*inscriptionTemp.getHolders())){
            customInscriptionMapper.updateStatus(inscription.getId());
        }
    }

    private void mintHandle(Inscription inscription, List<JSONObject> mintEventResponses, CollectionTransaction tx) {
        List<InscriptionInventory> inscriptionInventories = new ArrayList<>(mintEventResponses.size());

        List<TxInscriptionBak> txInscriptionBakList = new ArrayList<>(mintEventResponses.size());

        mintEventResponses.forEach(x->{
            InscriptionContract.MintEventResponse item = JSONObject.toJavaObject(x, InscriptionContract.MintEventResponse.class);
            InscriptionInventory inscriptionInventory = new InscriptionInventory();
            inscriptionInventory.setInscriptionId(inscription.getInscriptionId());
            inscriptionInventory.setNum(item._curPid.toString());
            inscriptionInventory.setOwner(item._user);
            inscriptionInventory.setTick(inscription.getTick());
            inscriptionInventory.setCreateTime(new Date());
            inscriptionInventory.setExt(item._randDesc);
            inscriptionInventory.setBalance(inscription.getLimitPerMint());
            InscriptionInventoryDetail inscriptionInventoryDetail = new InscriptionInventoryDetail();
            inscriptionInventoryDetail.setP("turn-inscription");
            inscriptionInventoryDetail.setOp("mint");
            inscriptionInventoryDetail.setLim(inscription.getLimitPerMint().toString());
            inscriptionInventoryDetail.setMax(inscription.getTotalSupply().toString());
            inscriptionInventoryDetail.setTick(inscription.getTick());
            inscriptionInventoryDetail.setExt(item._randDesc);
            inscriptionInventory.setDescription(JSONObject.toJSONString(inscriptionInventoryDetail));
            inscriptionInventories.add(inscriptionInventory);

            TxInscriptionBak txInscriptionBak = new TxInscriptionBak();
            txInscriptionBak.setInscriptionId(inscription.getInscriptionId());
            txInscriptionBak.setFrom(inscription.getContractAddress());
            txInscriptionBak.setTo(item._user);
            txInscriptionBak.setHash(tx.getHash());
            txInscriptionBak.setBn(tx.getNum());
            txInscriptionBak.setbTime(tx.getTime());
            txInscriptionBak.setContract(inscription.getContractAddress());
            txInscriptionBak.setSeq(tx.getSeq());
            txInscriptionBak.setTxFee(new BigDecimal(tx.getGasUsed()).multiply(new BigDecimal(tx.getGasLimit())).toPlainString());
            txInscriptionBak.setValue(inscription.getLimitPerMint().toString());
            txInscriptionBak.setNum(item._curPid.longValue());
            txInscriptionBak.setType(InscriptionTxTypeEnum.MINT.getCode());
            txInscriptionBak.setTick(inscription.getTick());
            txInscriptionBakList.add(txInscriptionBak);

            customInscriptionMapper.updateMinted(inscription.getContractAddress(),inscription.getLimitPerMint());
        });
        handleInscriptionHolder(txInscriptionBakList,inscription);
        updateInscriptionHolderCount(inscription);
        inscriptionInventoryMapper.batchInsert(inscriptionInventories);
        txInscriptionBakMapper.batchInsert(txInscriptionBakList);

        Set<TxInscriptionBak> txInscriptionBakSet = new HashSet<>(mintEventResponses.size());
        txInscriptionBakSet.addAll(txInscriptionBakList);
        try {
            esInscriptionTxService.save(txInscriptionBakSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Update the number of holders corresponding to the inscription
     *
     * @param
     * @param inscription
     * @return void
     */
    private void updateInscriptionHolderCount(Inscription inscription) {
        Integer holdCount = customInscriptionHolderMapper.inscriptionHolderCount(inscription.getInscriptionId());
        if (!inscription.getHolders().equals(holdCount)) {
            customInscriptionMapper.updateHolders(holdCount,inscription.getContractAddress());
        }
    }
    /**
     * parse Token Holder
     */
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void handleInscriptionHolder(List<TxInscriptionBak> txList, Inscription inscription) {
        List<InscriptionHolder> insertOrUpdate = new ArrayList<>();
        txList.forEach(tx -> resolveInscriptionHolder(tx.getTo(), tx, insertOrUpdate,inscription));
        if (CollUtil.isNotEmpty(insertOrUpdate)) {
            customInscriptionHolderMapper.batchInsertOrUpdateSelective(insertOrUpdate,
                    InscriptionHolder.Column.excludes(InscriptionHolder.Column.createTime,
                            InscriptionHolder.Column.updateTime));
        }
    }

    private void resolveInscriptionHolder(String ownerAddress, TxInscriptionBak txInscriptionBak, List<InscriptionHolder> insertOrUpdate, Inscription inscription) {

        InscriptionHolderKey key = getInscriptionHolderKey(ownerAddress, txInscriptionBak);
        InscriptionHolder inscriptionHolder = customInscriptionHolderMapper.selectByKey(key);
        if (inscriptionHolder == null) {
            inscriptionHolder = new InscriptionHolder();
            inscriptionHolder.setInscriptionId(txInscriptionBak.getInscriptionId());
            inscriptionHolder.setAddress(key.getAddress());
            inscriptionHolder.setBalance(inscription.getLimitPerMint().toString());
            inscriptionHolder.setMintTimes(1);
        } else {
            inscriptionHolder.setBalance(String.valueOf(Long.parseLong(inscriptionHolder.getBalance()) + inscription.getLimitPerMint()));
            inscriptionHolder.setMintTimes(inscriptionHolder.getMintTimes()+1);
        }
        log.info("The inscription contract address [{}], the holder's address [{}], and the holder's balance of transactions for this inscription contract is [{}]",
                inscriptionHolder.getInscriptionId(),
                inscriptionHolder.getAddress(),
                inscriptionHolder.getBalance());
        insertOrUpdate.add(inscriptionHolder);
    }

    private InscriptionHolderKey getInscriptionHolderKey(String ownerAddress, TxInscriptionBak txInscriptionBak) {
        InscriptionHolderKey inscriptionHolderKey = new InscriptionHolderKey();
        inscriptionHolderKey.setInscriptionId(txInscriptionBak.getInscriptionId());
        inscriptionHolderKey.setAddress(ownerAddress);
        return inscriptionHolderKey;
    }
}
