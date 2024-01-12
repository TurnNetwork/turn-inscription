package com.turn.inscription.controller.token;

import com.turn.inscription.enums.I18nEnum;
import com.turn.inscription.enums.RetEnum;
import com.turn.inscription.request.token.QueryTokenDetailReq;
import com.turn.inscription.request.token.QueryTokenListReq;
import com.turn.inscription.response.BaseResp;
import com.turn.inscription.response.RespPage;
import com.turn.inscription.response.token.QueryTokenDetailResp;
import com.turn.inscription.response.token.QueryTokenListResp;
import com.turn.inscription.service.TokenService;
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
@RequestMapping("token")
public class TokenController {

    @Resource
    private TokenService tokenService;

    @Resource
    private I18nUtil i18n;

    /**
     * token token list
     *
     * @param req
     * @return reactor.core.publisher.Mono<com.turn.browser.response.RespPage < com.turn.browser.response.token.QueryTokenListResp>>
     */
    @PostMapping("list")
    public Mono<RespPage<QueryTokenListResp>> list(@Valid @RequestBody QueryTokenListReq req) {
        return Mono.create(sink -> {
            RespPage<QueryTokenListResp> resp = tokenService.queryTokenList(req);
            sink.success(resp);
        });
    }

    /**
     * Token details
     *
     * @param req
     * @return reactor.core.publisher.Mono<com.turn.browser.response.BaseResp < com.turn.browser.response.token.QueryTokenDetailResp>>
     */
    @PostMapping("detail")
    public Mono<BaseResp<QueryTokenDetailResp>> detail(@Valid @RequestBody QueryTokenDetailReq req) {
        return Mono.create(sink -> {
            QueryTokenDetailResp resp = tokenService.queryTokenDetail(req);
            sink.success(BaseResp.build(RetEnum.RET_SUCCESS.getCode(), i18n.i(I18nEnum.SUCCESS), resp));
        });
    }

}
