package com.turn.inscription.controller.token;

import com.turn.inscription.config.CommonMethod;
import com.turn.inscription.config.DownFileCommon;
import com.turn.inscription.enums.I18nEnum;
import com.turn.inscription.enums.RetEnum;
import com.turn.inscription.exception.BusinessException;
import com.turn.inscription.request.inscription.QueryInscriptionTransferRecordListReq;
import com.turn.inscription.request.token.QueryTokenTransferRecordListReq;
import com.turn.inscription.response.BaseResp;
import com.turn.inscription.response.RespPage;
import com.turn.inscription.response.account.AccountDownload;
import com.turn.inscription.response.inscription.InscriptionStatisticResp;
import com.turn.inscription.response.microNode.MicroNodeStatisticResp;
import com.turn.inscription.response.token.QueryInscriptionTransferRecordListResp;
import com.turn.inscription.response.token.QueryTokenTransferRecordListResp;
import com.turn.inscription.service.ErcTxService;
import com.turn.inscription.service.InscriptionTxService;
import com.turn.inscription.utils.I18nUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("inscriptionTx")
public class InscriptionTxController {

    @Resource
    private I18nUtil i18n;

    @Resource
    private InscriptionTxService inscriptionTxService;

    /**
     * inscrption transaction list
     *
     * @param req
     * @return reactor.core.publisher.Mono<com.turn.browser.response.RespPage < com.turn.browser.response.token.QueryInscriptionTransferRecordListResp>>
     */
    @PostMapping("list")
    public Mono<RespPage<QueryInscriptionTransferRecordListResp>> list(@Valid @RequestBody QueryInscriptionTransferRecordListReq req) {
        return Mono.just(inscriptionTxService.getList(req));
    }
}
