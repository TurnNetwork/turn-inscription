package com.turn.inscription.controller;

import com.turn.inscription.enums.I18nEnum;
import com.turn.inscription.enums.RetEnum;
import com.turn.inscription.response.BaseResp;
import com.turn.inscription.response.extra.QueryConfigResp;
import com.turn.inscription.service.ExtraService;
import com.turn.inscription.utils.I18nUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * controller implementation
 */
@Slf4j
@RestController
public class ExtraController {

    @Resource
    private ExtraService extraService;

    @Resource
    private I18nUtil i18n;

    /**
     * Query configuration details
     *
     * @param
     * @return reactor.core.publisher.Mono<com.turn.browser.response.BaseResp < com.turn.browser.response.extra.QueryConfigResp>>
     */
    @PostMapping("extra/queryConfig")
    public Mono<BaseResp<QueryConfigResp>> queryConfig() {
        return Mono.create(sink -> {
            QueryConfigResp resp = extraService.queryConfig();
            sink.success(BaseResp.build(RetEnum.RET_SUCCESS.getCode(), i18n.i(I18nEnum.SUCCESS), resp));
        });
    }

}
