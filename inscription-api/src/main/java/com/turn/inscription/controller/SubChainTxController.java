package com.turn.inscription.controller;


import com.turn.inscription.enums.I18nEnum;
import com.turn.inscription.enums.RetEnum;
import com.turn.inscription.request.PageReq;
import com.turn.inscription.request.subchain.SubChainRecordListReq;
import com.turn.inscription.request.subchain.SubChainTransactionDetailsReq;
import com.turn.inscription.response.BaseResp;
import com.turn.inscription.response.RespPage;
import com.turn.inscription.response.subchain.SubChainTxDetailsResp;
import com.turn.inscription.response.subchain.SubChainTxRecordListResp;
import com.turn.inscription.service.SubChainTxService;
import com.turn.inscription.utils.I18nUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 微节点控制器
 */
@Slf4j
@RestController
public class SubChainTxController {

    @Resource
    private I18nUtil i18n;

    @Resource
    private SubChainTxService subChainTxService;

    /**
     * transaction list
     * @param req
     */
    @PostMapping("subchain/subChainTxList")
    public Mono<RespPage<SubChainTxRecordListResp>> transactionList(@Valid @RequestBody PageReq req) {
        return Mono.just(subChainTxService.getTransactionList(req));
    }

    /**
     * Sub-chain transaction list
     * @param req
     * @return
     */
    @PostMapping("subchain/subChainTxRecordList")
    public Mono<RespPage<SubChainTxRecordListResp>> subChainTxRecordList(@Valid @RequestBody SubChainRecordListReq req) {
        return Mono.just(subChainTxService.subChainTxRecordList(req));
    }

    /**
     * Sub-chain transaction details
     *
     * @param req
     * @return reactor.core.publisher.Mono<com.turn.browser.response.BaseResp < com.turn.browser.response.transaction.TransactionDetailsResp>>
     */
    @PostMapping("subchain/subChainTxDetails")
    public Mono<BaseResp<SubChainTxDetailsResp>> subChainTxDetails(@Valid @RequestBody SubChainTransactionDetailsReq req) {
        return Mono.create(sink -> {
            SubChainTxDetailsResp resp = subChainTxService.subChainTxDetails(req);
            sink.success(BaseResp.build(RetEnum.RET_SUCCESS.getCode(), i18n.i(I18nEnum.SUCCESS), resp));
        });
    }

}
