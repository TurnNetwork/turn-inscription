package com.turn.inscription.service;

import cn.hutool.core.util.StrUtil;
import com.turn.inscription.enums.I18nEnum;
import com.turn.inscription.enums.RetEnum;
import com.turn.inscription.request.address.QueryAddrGameListReq;
import com.turn.inscription.response.BaseResp;
import com.turn.inscription.response.address.AddrGameDetailResp;
import com.turn.inscription.utils.I18nUtil;
import com.turn.inscription.v0152.bean.AddrGameDetailDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * Specific logic implementation method for address participation in the game
 */
@Service
public class AddrGameService {

    private final Logger logger = LoggerFactory.getLogger(AddrGameService.class);

    @Resource
    private AddrGameCacheService addrGameCacheService;

    @Resource
    private I18nUtil i18n;

    /**
     * query address participation details
     *
     * @param req
     * @return com.turn.browser.response.address.QueryDetailResp
     */
    public BaseResp<List<AddrGameDetailResp>> getList(QueryAddrGameListReq req) {
        BaseResp<List<AddrGameDetailResp>> baseResp = new BaseResp<>();
        // If you query the 0 address, return directly
        if (StrUtil.isNotBlank(req.getAddress()) && com.turn.inscription.utils.AddressUtil.isAddrZero(req.getAddress())) {
            return baseResp;
        }
        List<AddrGameDetailDto> addrGameList = addrGameCacheService.getAddrGameCache(req.getAddress());

        List<AddrGameDetailResp> lists = new LinkedList<>();
        for (AddrGameDetailDto addrGameDetailDto : addrGameList) {
            AddrGameDetailResp resp = new AddrGameDetailResp();
            BeanUtils.copyProperties(addrGameDetailDto, resp);
            lists.add(resp);
        }
        return BaseResp.build(RetEnum.RET_SUCCESS.getCode(), i18n.i(I18nEnum.SUCCESS),lists);
    }

}
