package com.turn.inscription.analyzer.ppos;

import com.turn.inscription.bean.CollectionEvent;
import com.turn.inscription.dao.entity.GasEstimate;
import com.turn.inscription.dao.custommapper.CustomGasEstimateMapper;
import com.turn.inscription.dao.custommapper.DelegateBusinessMapper;
import com.turn.inscription.dao.param.ppos.DelegateCreate;
import com.turn.inscription.elasticsearch.dto.Transaction;
import com.turn.inscription.param.DelegateCreateParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Delegate business parameter converter
 **/
@Slf4j
@Service
public class DelegateCreateAnalyzer extends PPOSAnalyzer<DelegateCreate> {

    @Resource
    private DelegateBusinessMapper delegateBusinessMapper;

    @Resource
    private CustomGasEstimateMapper customGasEstimateMapper;

    /**
     * Initiate a commission (commission)
     */
    @Override
    public DelegateCreate analyze(CollectionEvent event, Transaction tx) {
        // Initiate a commission
        DelegateCreateParam txParam = tx.getTxParam(DelegateCreateParam.class);
        // Supplementary node name
        updateTxInfo(txParam, tx);
        // Failed transactions do not analyze business data
        if (Transaction.StatusEnum.FAILURE.getCode() == tx.getStatus())
            return null;

        long startTime = System.currentTimeMillis();

        DelegateCreate businessParam = DelegateCreate.builder()
                .nodeId(txParam.getNodeId())
                .amount(txParam.getAmount())
                .blockNumber(BigInteger.valueOf(tx.getNum()))
                .txFrom(tx.getFrom())
                .sequence(BigInteger.valueOf(tx.getSeq()))
                .stakingBlockNumber(txParam.getStakingBlockNum())
                .build();

        delegateBusinessMapper.create(businessParam);

        // 1. Added estimated gas commission uncalculated cycle epoch = 0: directly stored in the mysql database
        List<GasEstimate> estimates = new ArrayList<>();
        GasEstimate estimate = new GasEstimate();
        estimate.setNodeId(txParam.getNodeId());
        estimate.setSbn(txParam.getStakingBlockNum().longValue());
        estimate.setAddr(tx.getFrom());
        estimate.setEpoch(0L);
        estimates.add(estimate);
        customGasEstimateMapper.batchInsertOrUpdateSelective(estimates, GasEstimate.Column.values());

        log.debug("Processing time:{} ms", System.currentTimeMillis() - startTime);
        return businessParam;
    }

}
