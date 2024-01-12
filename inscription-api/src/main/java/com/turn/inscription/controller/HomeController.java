package com.turn.inscription.controller;

import com.turn.inscription.enums.I18nEnum;
import com.turn.inscription.enums.RetEnum;
import com.turn.inscription.request.home.QueryNavigationRequest;
import com.turn.inscription.response.BaseResp;
import com.turn.inscription.response.home.BlockStatisticNewResp;
import com.turn.inscription.response.home.ChainStatisticNewResp;
import com.turn.inscription.response.home.QueryNavigationResp;
import com.turn.inscription.response.home.StakingListNewResp;
import com.turn.inscription.service.CommonService;
import com.turn.inscription.service.HomeService;
import com.turn.inscription.utils.I18nUtil;
import com.bubble.utils.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Home module Contract. Define usage
 */
@Slf4j
@RestController
public class HomeController {

    @Resource
    private I18nUtil i18n;

    @Resource
    private HomeService homeService;

    @Resource
    private CommonService commonService;

    /**
     * Search navigation
     *
     * @param req
     * @return reactor.core.publisher.Mono<com.turn.browser.response.BaseResp < com.turn.browser.response.home.QueryNavigationResp>>
     */
    @PostMapping("home/queryNavigation")
    public Mono<BaseResp<QueryNavigationResp>> queryNavigation(@Valid @RequestBody QueryNavigationRequest req) {
        return Mono.create(sink -> {
            QueryNavigationResp resp = homeService.queryNavigation(req);
            sink.success(BaseResp.build(RetEnum.RET_SUCCESS.getCode(), i18n.i(I18nEnum.SUCCESS), resp));
        });
    }

    /**
     * Block trend
     *
     * @param
     * @return reactor.core.publisher.Mono<com.turn.browser.response.BaseResp < com.turn.browser.response.home.BlockStatisticNewResp>>
     */
    @SubscribeMapping("topic/block/statistic/new")
    @PostMapping("home/blockStatistic")
    public Mono<BaseResp<BlockStatisticNewResp>> blockStatisticNew() {
        return Mono.create(sink -> {
            BlockStatisticNewResp blockStatisticNewResp = homeService.blockStatisticNew();
            sink.success(BaseResp.build(RetEnum.RET_SUCCESS.getCode(), i18n.i(I18nEnum.SUCCESS), blockStatisticNewResp));
        });
    }

    @SubscribeMapping("/topic/chain/statistic/new")
    @PostMapping("home/chainStatistic")
    public Mono<BaseResp<ChainStatisticNewResp>> chainStatisticNew() {
        return Mono.create(sink -> {
            ChainStatisticNewResp chainStatisticNewResp = homeService.chainStatisticNew();
            sink.success(BaseResp.build(RetEnum.RET_SUCCESS.getCode(), i18n.i(I18nEnum.SUCCESS), chainStatisticNewResp));
        });
    }

    @SubscribeMapping("topic/staking/list/new")
    @PostMapping("home/stakingList")
    public Mono<BaseResp<StakingListNewResp>> stakingListNew() {
        return Mono.create(sink -> {
            StakingListNewResp stakingListNewResp = homeService.stakingListNew();
            /**
             * The first return is set to true
             */
            stakingListNewResp.setIsRefresh(true);
            sink.success(BaseResp.build(RetEnum.RET_SUCCESS.getCode(), i18n.i(I18nEnum.SUCCESS), stakingListNewResp));
        });
    }

    /**
     * Get total circulation(AAA)
     *
     * @return: reactor.core.publisher.Mono<java.lang.String>
     */
    @GetMapping("home/issueValue")
    public Mono<String> getIssueValue() {
        return Mono.create(sink -> {
            BigDecimal issueValue = commonService.getIssueValue();
            issueValue = Convert.fromVon(issueValue, Convert.Unit.KPVON).setScale(8, RoundingMode.DOWN);
            if (issueValue.compareTo(BigDecimal.ZERO) <= 0) {
                sink.error(new Exception("Get total circulation exception"));
            }
            sink.success(issueValue.toPlainString());
        });
    }

    /**
     * Get circulation(AAA)
     *
     * @return: reactor.core.publisher.Mono<java.lang.String>
     */
    @GetMapping("home/circulationValue")
    public Mono<String> getCirculationValue() {
        return Mono.create(sink -> {
            BigDecimal circulationValue = commonService.getCirculationValue();
            circulationValue = Convert.fromVon(circulationValue, Convert.Unit.KPVON).setScale(8, RoundingMode.DOWN);
            sink.success(circulationValue.toPlainString());
        });
    }

}
