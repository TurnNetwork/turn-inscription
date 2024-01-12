package com.turn.inscription.service;

import com.github.pagehelper.Page;
import com.turn.inscription.bean.SubChainTx;
import com.turn.inscription.cache.SubChainTransactionCacheDto;
import com.turn.inscription.dao.entity.NetworkStat;
import com.turn.inscription.elasticsearch.dto.Transaction;
import com.turn.inscription.enums.*;
import com.turn.inscription.request.PageReq;
import com.turn.inscription.request.subchain.SubChainRecordListReq;
import com.turn.inscription.request.subchain.SubChainTransactionDetailsReq;
import com.turn.inscription.response.RespPage;
import com.turn.inscription.response.subchain.SubChainTxDetailsResp;
import com.turn.inscription.response.subchain.SubChainTxRecordListResp;
import com.turn.inscription.service.elasticsearch.EsSubChainTxRepository;
import com.turn.inscription.service.elasticsearch.bean.ESResult;
import com.turn.inscription.service.elasticsearch.query.ESQueryBuilderConstructor;
import com.turn.inscription.service.elasticsearch.query.ESQueryBuilders;
import com.turn.inscription.utils.I18nUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


/**
 * 子链交易模块方法
 */
@Service
public class SubChainTxService {

    private final Logger logger = LoggerFactory.getLogger(SubChainTxService.class);

    @Resource
    private EsSubChainTxRepository esSubChainTxRepository;

    @Resource
    private I18nUtil i18n;

    @Resource
    private StatisticCacheService statisticCacheService;

    private static final String ERROR_TIPS = "Getting block error.";

    public RespPage<SubChainTxRecordListResp> subChainTxRecordList(SubChainRecordListReq req) {
        RespPage<SubChainTxRecordListResp> respPage = new RespPage<>();
        ESQueryBuilderConstructor constructor = new ESQueryBuilderConstructor();
        ESResult<SubChainTx> items;

        constructor.buildMust(new BoolQueryBuilder().should(QueryBuilders.termQuery("from", req.getAddress()))
                .should(QueryBuilders.termQuery("to", req.getAddress())));
        constructor.setDesc("seq");
        constructor.setUnmappedType("long");
        constructor.setResult(new String[]{
                "hash",
                "time",
                "status",
                "from",
                "to",
                "value",
                "num",
                "type",
                "toType",
                "cost",
                "bubbleId",
                "failReason"});
        try {
            items = this.esSubChainTxRepository.search(constructor, SubChainTx.class, req.getPageNo(), req.getPageSize());
        } catch (Exception e) {
            logger.error("获取节点操作错误。", e);
            return respPage;
        }
        List<SubChainTx> subChainTxes = items.getRsData();
        List<SubChainTxRecordListResp> lists = new LinkedList<>();
        for (SubChainTx subChainTx : subChainTxes) {
            SubChainTxRecordListResp subChainTxRecordListResp = new SubChainTxRecordListResp();
            BeanUtils.copyProperties(subChainTx, subChainTxRecordListResp);
            subChainTxRecordListResp.setTxType(String.valueOf(subChainTx.getType()));
            subChainTxRecordListResp.setTimestamp(subChainTx.getTime().getTime());
            subChainTxRecordListResp.setBlockNumber(subChainTx.getNum());
            subChainTxRecordListResp.setTxHash(subChainTx.getHash());
            lists.add(subChainTxRecordListResp);
        }
        /** 查询分页总数 */
        Page<?> page = new Page<>(req.getPageNo(), req.getPageSize());
        page.setTotal(items.getTotal());
        respPage.init(page, lists);
        return respPage;
    }

    public SubChainTxDetailsResp subChainTxDetails(SubChainTransactionDetailsReq req) {
        SubChainTxDetailsResp resp = new SubChainTxDetailsResp();

        ESQueryBuilderConstructor constructor = new ESQueryBuilderConstructor();
        constructor.must(new ESQueryBuilders().term("bubbleId", req.getBubbleId()));
        constructor.must(new ESQueryBuilders().term("hash", req.getTxHash()));
        ESResult<SubChainTx> subChainTxESResult = new ESResult<>();

        try {
            subChainTxESResult = esSubChainTxRepository.search(constructor, SubChainTx.class, 1, 1);
        } catch (Exception e) {
            this.logger.error(ERROR_TIPS, e);
            return resp;
        }
        if (subChainTxESResult != null) {
            SubChainTx subChainTx = subChainTxESResult.getRsData().get(0);
            BeanUtils.copyProperties(subChainTx, resp);
            resp.setActualTxCost(new BigDecimal(subChainTx.getCost()));
            resp.setBlockNumber(subChainTx.getNum());
            resp.setGasLimit(subChainTx.getGasLimit());
            resp.setGasUsed(subChainTx.getGasUsed());
            resp.setTxType(String.valueOf(Transaction.TypeEnum.CONTRACT_EXEC.getCode()));
            resp.setTxHash(subChainTx.getHash());
            resp.setTimestamp(subChainTx.getTime().getTime());
            resp.setServerTime(System.currentTimeMillis());
            resp.setTxInfo("");
            resp.setGasPrice(new BigDecimal(subChainTx.getGasPrice()));
            resp.setValue(new BigDecimal(subChainTx.getValue()));
            resp.setSubChainTopics(subChainTx.getSubChainTopics());
            resp.setContractName("");
            /**
             * 失败信息国际化
             */
            String name = "CODE" + subChainTx.getFailReason();

            I18nEnum i18nEnum = I18nEnum.getEnum(name);
            if (i18nEnum != null) {
                resp.setFailReason(this.i18n.i(i18nEnum));
            }
            if (Transaction.StatusEnum.FAILURE.getCode() == subChainTx.getStatus()) {
                resp.setTxReceiptStatus(0);
            } else {
                resp.setTxReceiptStatus(subChainTx.getStatus());
            }

        }
        return resp;
    }

    public RespPage<SubChainTxRecordListResp> getTransactionList(PageReq req) {
        RespPage<SubChainTxRecordListResp> result = new RespPage<>();
        /** Query redis sub chain transaction data by page */
        SubChainTransactionCacheDto transactionCacheDto = this.statisticCacheService.getSubChainTransactionCache(req.getPageNo(),
                req.getPageSize());
        List<SubChainTx> items = transactionCacheDto.getTransactionList();
        /**
         * data conversion
         */
        List<SubChainTxRecordListResp> lists = this.transferList(items);
        NetworkStat networkStat = this.statisticCacheService.getNetworkStatCache();
        result.init(lists,
                null == networkStat.getTxQty() ? 0 : networkStat.getTxQty(),
                transactionCacheDto.getPage().getTotalCount(),
                transactionCacheDto.getPage().getTotalPages());
        return result;
    }

    private List<SubChainTxRecordListResp> transferList(List<SubChainTx> items) {
        List<SubChainTxRecordListResp> lists = new LinkedList<>();
        for (SubChainTx transaction : items) {
            SubChainTxRecordListResp transactionListResp = new SubChainTxRecordListResp();
            BeanUtils.copyProperties(transaction, transactionListResp);
            transactionListResp.setTxHash(transaction.getHash());
            transactionListResp.setActualTxCost(new BigDecimal(transaction.getCost()));
            transactionListResp.setBlockNumber(transaction.getNum());
            transactionListResp.setReceiveType(String.valueOf(transaction.getToType()));
            /**
             * wasm is also a contract creation
             */
            if (transaction.getType() == Transaction.TypeEnum.WASM_CONTRACT_CREATE.getCode()) {
                transactionListResp.setTxType(String.valueOf(Transaction.TypeEnum.EVM_CONTRACT_CREATE.getCode()));
            } else {
                transactionListResp.setTxType(String.valueOf(transaction.getType()));
            }
            transactionListResp.setServerTime(System.currentTimeMillis());
            transactionListResp.setTimestamp(transaction.getTime().getTime());
            transactionListResp.setValue(new BigDecimal(transaction.getValue()));
            /**
             * Failure information internationalization
             */
            I18nEnum i18nEnum = I18nEnum.getEnum("CODE" + transaction.getFailReason());
            if (i18nEnum != null) {
                transactionListResp.setFailReason(this.i18n.i(i18nEnum));
            }
            if (Transaction.StatusEnum.FAILURE.getCode() == transaction.getStatus()) {
                transactionListResp.setTxReceiptStatus(0);
            } else {
                transactionListResp.setTxReceiptStatus(transaction.getStatus());
            }
            lists.add(transactionListResp);
        }
        return lists;
    }

}
