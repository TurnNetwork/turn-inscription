package com.turn.inscription.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.turn.inscription.bean.CustomTokenHolder;
import com.turn.inscription.bean.Token1155HolderListBean;
import com.turn.inscription.cache.InscriptionTransferRecordCacheDto;
import com.turn.inscription.cache.TokenTransferRecordCacheDto;
import com.turn.inscription.config.DownFileCommon;
import com.turn.inscription.dao.custommapper.CustomToken1155HolderMapper;
import com.turn.inscription.dao.custommapper.CustomTokenHolderMapper;
import com.turn.inscription.dao.entity.*;
import com.turn.inscription.dao.mapper.AddressMapper;
import com.turn.inscription.dao.mapper.InscriptionMapper;
import com.turn.inscription.dao.mapper.TokenInventoryMapper;
import com.turn.inscription.dao.mapper.TokenMapper;
import com.turn.inscription.elasticsearch.dto.ErcTx;
import com.turn.inscription.enums.ErcTypeEnum;
import com.turn.inscription.enums.I18nEnum;
import com.turn.inscription.enums.TokenTypeEnum;
import com.turn.inscription.request.inscription.QueryInscriptionTransferRecordListReq;
import com.turn.inscription.request.token.QueryHolderTokenListReq;
import com.turn.inscription.request.token.QueryTokenHolderListReq;
import com.turn.inscription.request.token.QueryTokenTransferRecordListReq;
import com.turn.inscription.response.RespPage;
import com.turn.inscription.response.account.AccountDownload;
import com.turn.inscription.response.inscription.InscriptionStatisticResp;
import com.turn.inscription.response.token.QueryHolderTokenListResp;
import com.turn.inscription.response.token.QueryInscriptionTransferRecordListResp;
import com.turn.inscription.response.token.QueryTokenHolderListResp;
import com.turn.inscription.response.token.QueryTokenTransferRecordListResp;
import com.turn.inscription.service.elasticsearch.*;
import com.turn.inscription.service.elasticsearch.bean.ESResult;
import com.turn.inscription.service.elasticsearch.query.ESQueryBuilderConstructor;
import com.turn.inscription.service.elasticsearch.query.ESQueryBuilders;
import com.turn.inscription.utils.ConvertUtil;
import com.turn.inscription.utils.DateUtil;
import com.turn.inscription.utils.HexUtil;
import com.turn.inscription.utils.I18nUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Inscription transaction records within the contract
 */
@Service
@Slf4j
public class InscriptionTxService {

    @Resource
    private EsInscriptionTxRepository esInscriptionTxRepository;

    public RespPage<QueryInscriptionTransferRecordListResp> getList(QueryInscriptionTransferRecordListReq req) {
        if (log.isDebugEnabled()) {
            log.debug("~ queryTokenRecordList, params: " + JSON.toJSONString(req));
        }
        // logic:
        // 1. In the internal transaction list of the contract, the data is stored in ES. The list is obtained through ES.
        // 2. All queries go directly to ES without DB retrieval.
        RespPage<QueryInscriptionTransferRecordListResp> result = new RespPage<>();
        // If you query the 0 address, return directly
        if (StrUtil.isNotBlank(req.getAddress()) && com.turn.inscription.utils.AddressUtil.isAddrZero(req.getAddress())) {
            return result;
        }
        List<TxInscriptionBak> records;
        long totalCount = 0;
        long displayTotalCount = 0;
        // construct of params
        ESQueryBuilderConstructor constructor = new ESQueryBuilderConstructor();
        ESResult<TxInscriptionBak> queryResultFromES = new ESResult<>();
        // condition: txHash/contract/txFrom/transferTo
        if (StrUtil.isNotBlank(req.getInscriptionId())) {
            // Compatible with old data
            constructor.buildMust(new BoolQueryBuilder().should(QueryBuilders.termQuery("inscriptionId", req.getInscriptionId())).should(QueryBuilders.termQuery("value", req.getInscriptionId())));
        }
        if (StringUtils.isNotEmpty(req.getAddress())) {
            constructor.buildMust(new BoolQueryBuilder().should(QueryBuilders.termQuery("from", req.getAddress())).should(QueryBuilders.termQuery("to", req.getAddress())));
        }
        if (StringUtils.isNotEmpty(req.getTxHash())) {
            constructor.must(new ESQueryBuilders().term("hash", req.getTxHash()));
        }
        if (ObjectUtil.isNotNull(req.getType())) {
            constructor.must(new ESQueryBuilders().term("type", req.getType()));
        }
        // Set sort field
        constructor.setDesc("seq");
        // response filed to show.
        constructor.setResult(new String[]{"seq", "hash", "bn", "from", "contract", "to", "inscriptionId", "value", "result", "bTime", "num", "type", "tick"});

        ESQueryBuilderConstructor count = new ESQueryBuilderConstructor();

        if (StringUtils.isNotEmpty(req.getAddress())) {
            count.buildMust(new BoolQueryBuilder().should(QueryBuilders.termQuery("from", req.getAddress())).should(QueryBuilders.termQuery("to", req.getAddress())));
        }
        if (StrUtil.isNotBlank(req.getInscriptionId())) {
            // Compatible with old data
            count.buildMust(new BoolQueryBuilder().should(QueryBuilders.termQuery("inscriptionId", req.getInscriptionId())));
        }
        if (ObjectUtil.isNotNull(req.getType())) {
            count.must(new ESQueryBuilders().term("type", req.getType()));
        }

        try {
            queryResultFromES = esInscriptionTxRepository.search(constructor, TxInscriptionBak.class, req.getPageNo(), req.getPageSize());
            ESResult<?> res = esInscriptionTxRepository.Count(count);
            totalCount = res.getTotal();
            displayTotalCount = res.getTotal();
        } catch (Exception e) {
            log.error("Failed to retrieve token transaction list", e);
            return result;
        }

        records = queryResultFromES.getRsData();
        if (null == records || records.size() == 0) {
            log.debug("No valid data was retrieved, parameters:" + JSON.toJSONString(req));
            return result;
        }

        List<QueryInscriptionTransferRecordListResp> recordListResp = records.parallelStream()
                                                                       .filter(Objects::nonNull)
                                                                       .map(p -> this.toQueryInscriptionTransferRecordListResp(req.getAddress(), p))
                                                                       .collect(Collectors.toList());
        result.init(recordListResp, totalCount, displayTotalCount, totalCount / req.getPageSize() + 1);
        return result;
    }

    public QueryInscriptionTransferRecordListResp toQueryInscriptionTransferRecordListResp(String address, TxInscriptionBak record) {
        QueryInscriptionTransferRecordListResp resp = QueryInscriptionTransferRecordListResp.builder()
                                                                                .seq(record.getSeq())
                                                                                .txHash(record.getHash())
                                                                                .blockNumber(record.getBn())
                                                                                .txFrom(record.getFrom())
                                                                                .contract(record.getContract())
                                                                                .transferTo(record.getTo())
                                                                                .inscriptionId(record.getInscriptionId())
                                                                                .transferValue(new BigDecimal(record.getValue()))
                                                                                .blockTimestamp(record.getbTime())
                                                                                .systemTimestamp(System.currentTimeMillis())
                                                                                .num(record.getNum())
                                                                                .type(record.getType())
                                                                                .build();
        // Processing accuracy calculation.
        if (null == record.getValue()) {
            resp.setTransferValue(BigDecimal.ZERO);
        }

        return resp;
    }

    private BigDecimal getAddressBalance(CustomTokenHolder tokenHolder) {
        //The balance is temporarily calculated by the background
        return new BigDecimal(tokenHolder.getBalance());
    }
}
