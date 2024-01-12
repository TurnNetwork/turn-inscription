package com.turn.inscription.controller.token;

import com.turn.inscription.request.inscription.*;
import com.turn.inscription.response.BaseResp;
import com.turn.inscription.response.RespPage;
import com.turn.inscription.response.inscription.*;
import com.turn.inscription.service.InscriptionService;
import com.turn.inscription.utils.I18nUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("inscription")
public class InscriptionController {

    @Resource
    private I18nUtil i18n;

    @Resource
    private InscriptionService inscriptionService;

    /**
     * inscrption transaction list
     *
     * @param req
     * @return reactor.core.publisher.Mono<com.turn.browser.response.RespPage < com.turn.browser.response.token.QueryInscriptionTransferRecordListResp>>
     */
    @PostMapping("/list")
    public Mono<RespPage<InscriptionListResp>> list(@Valid @RequestBody InscriptionListReq req) {
        return Mono.just(inscriptionService.list(req));
    }

    /**
     * inscription details
     *
     * @param req
     * @return reactor.core.publisher.Mono<com.turn.browser.response.BaseResp < com.turn.browser.response.staking.InscriptionDetailsResp>>
     */
    @PostMapping("/details")
    public Mono<BaseResp<InscriptionDetailsResp>> details(@Valid @RequestBody InscriptionDetailsReq req) {
        return Mono.just(inscriptionService.details(req));
    }

    /**
     * inscrption holders list
     *
     * @param req
     * @return reactor.core.publisher.Mono<com.turn.browser.response.RespPage < com.turn.browser.response.token.QueryInscriptionTransferRecordListResp>>
     */
    @PostMapping("/holders")
    public Mono<RespPage<InscriptionHolderListResp>> holders(@Valid @RequestBody InscriptionHolderListReq req) {
        return Mono.just(inscriptionService.holderList(req));
    }

    /**
     * inscrption Inventory list
     *
     * @param req
     * @return reactor.core.publisher.Mono<com.turn.browser.response.RespPage < com.turn.browser.response.token.QueryInscriptionTransferRecordListResp>>
     */
    @PostMapping("/inventory")
    public Mono<RespPage<InscriptionInventoryListResp>> inventory(@Valid @RequestBody InscriptionInventoryListReq req) {
        return Mono.just(inscriptionService.inventoryList(req));
    }

    /**
     * inscrption holder
     *
     * @param req
     * @return reactor.core.publisher.Mono<com.turn.browser.response.RespPage < com.turn.browser.response.token.QueryInscriptionTransferRecordListResp>>
     */
    @PostMapping("/holder")
    public Mono<RespPage<InscriptionHolderResp>> holder(@Valid @RequestBody InscriptionHolderReq req) {
        return Mono.just(inscriptionService.holder(req));
    }
}
