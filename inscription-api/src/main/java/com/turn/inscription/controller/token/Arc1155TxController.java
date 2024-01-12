package com.turn.inscription.controller.token;

import com.turn.inscription.config.CommonMethod;
import com.turn.inscription.config.DownFileCommon;
import com.turn.inscription.enums.I18nEnum;
import com.turn.inscription.exception.BusinessException;
import com.turn.inscription.request.token.QueryTokenTransferRecordListReq;
import com.turn.inscription.response.RespPage;
import com.turn.inscription.response.account.AccountDownload;
import com.turn.inscription.response.token.QueryTokenTransferRecordListResp;
import com.turn.inscription.service.ErcTxService;
import com.turn.inscription.utils.I18nUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("token/arc1155-tx")
public class Arc1155TxController {

    @Resource
    private I18nUtil i18n;

    @Resource
    private ErcTxService ercTxService;

    @Resource
    private DownFileCommon downFileCommon;

    @Resource
    private CommonMethod commonMethod;

    /**
     * ARC1155 transaction list
     *
     * @param req
     * @return reactor.core.publisher.Mono<com.turn.browser.response.RespPage < com.turn.browser.response.token.QueryTokenTransferRecordListResp>>
     */
    @PostMapping("list")
    public Mono<RespPage<QueryTokenTransferRecordListResp>> list(@Valid @RequestBody QueryTokenTransferRecordListReq req) {
        return Mono.just(ercTxService.token1155TransferList(req));
    }

    /**
     * arc1155 transaction list export
     *
     * @param address  wallet address
     * @param contract Contract address
     * @param date     start date timestamp
     * @param local    Region: en or zh-cn
     * @param timeZone Time zone: +8
     * @param token
     * @param tokenId  token id
     * @param response
     * @return void
     */
    @GetMapping("export")
    public void export(@RequestParam(value = "address", required = false) String address, @RequestParam(value = "contract", required = false) String contract, @RequestParam(value = "date", required = true) Long date, @RequestParam(value = "local", required = true) String local, @RequestParam(value = "timeZone", required = true) String timeZone, @RequestParam(value = "token", required = false) String token, @RequestParam(value = "tokenId", required = false) String tokenId, HttpServletResponse response) {
        try {
            /**
             * Authentication
             */
            commonMethod.recaptchaAuth(token);
            AccountDownload accountDownload = ercTxService.exportToken1155TransferList(address, contract, date, local, timeZone, tokenId);
            downFileCommon.download(response, accountDownload.getFilename(), accountDownload.getLength(), accountDownload.getData());
        } catch (Exception e) {
            log.error("download error", e);
            throw new BusinessException(this.i18n.i(I18nEnum.DOWNLOAD_EXCEPTION));
        }
    }

}
