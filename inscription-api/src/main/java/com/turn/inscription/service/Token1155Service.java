package com.turn.inscription.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.turn.inscription.bean.TokenIdListBean;
import com.turn.inscription.config.DownFileCommon;
import com.turn.inscription.dao.custommapper.CustomToken1155HolderMapper;
import com.turn.inscription.dao.custommapper.CustomToken1155InventoryMapper;
import com.turn.inscription.dao.entity.*;
import com.turn.inscription.dao.mapper.Token1155HolderMapper;
import com.turn.inscription.enums.I18nEnum;
import com.turn.inscription.request.token.QueryTokenIdDetailReq;
import com.turn.inscription.request.token.QueryTokenIdListReq;
import com.turn.inscription.response.RespPage;
import com.turn.inscription.response.account.AccountDownload;
import com.turn.inscription.response.token.QueryTokenIdDetailResp;
import com.turn.inscription.response.token.QueryTokenIdListResp;
import com.turn.inscription.utils.I18nUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class Token1155Service {

    @Resource
    private CustomToken1155HolderMapper customToken1155HolderMapper;

    @Resource
    private Token1155HolderMapper token1155HolderMapper;

    @Resource
    private CustomToken1155InventoryMapper customToken1155InventoryMapper;

    @Resource
    private I18nUtil i18n;

    @Resource
    private DownFileCommon downFileCommon;

    /**
     * ARC1155 Stock List
     *
     * @param req
     * @return com.turn.browser.response.RespPage<com.turn.browser.response.token.QueryTokenIdListResp>
     */
    public RespPage<QueryTokenIdListResp> queryTokenIdList(QueryTokenIdListReq req) {
        RespPage<QueryTokenIdListResp> result = new RespPage<>();
        Token1155HolderKey key = new Token1155HolderKey();
        if (StrUtil.isNotBlank(req.getContract())) {
            key.setTokenAddress(req.getContract());
        }
        if (StrUtil.isNotBlank(req.getAddress())) {
            key.setAddress(req.getAddress());
        }
        if (StrUtil.isNotBlank(req.getTokenId())) {
            key.setTokenId(req.getTokenId());
        }
        PageHelper.startPage(req.getPageNo(), req.getPageSize());
        Page<TokenIdListBean> tokenIdList = customToken1155HolderMapper.findTokenIdList(key);
        List<QueryTokenIdListResp> list = new ArrayList<>();
        if (CollUtil.isNotEmpty(tokenIdList)) {
            for (TokenIdListBean tokenIdListBean : tokenIdList) {
                QueryTokenIdListResp queryTokenIdListResp = new QueryTokenIdListResp();
                queryTokenIdListResp.setAddress(tokenIdListBean.getAddress());
                queryTokenIdListResp.setContract(tokenIdListBean.getContract());
                queryTokenIdListResp.setTokenId(tokenIdListBean.getTokenId());
                queryTokenIdListResp.setImage(tokenIdListBean.getImage());
                queryTokenIdListResp.setTxCount(tokenIdListBean.getTxCount());
                queryTokenIdListResp.setBalance(tokenIdListBean.getBalance());
                list.add(queryTokenIdListResp);
            }
        }
        result.init(tokenIdList, list);
        return result;
    }

    public QueryTokenIdDetailResp queryTokenIdDetail(QueryTokenIdDetailReq req) {
        Token1155InventoryKey tokenInventoryKey = new Token1155InventoryKey();
        tokenInventoryKey.setTokenAddress(req.getContract());
        tokenInventoryKey.setTokenId(req.getTokenId());
        Token1155InventoryWithBLOBs token1155Inventory = customToken1155InventoryMapper.findOneByUK(tokenInventoryKey);
        QueryTokenIdDetailResp queryTokenIdDetailResp = new QueryTokenIdDetailResp();
        queryTokenIdDetailResp.setAddress("");
        queryTokenIdDetailResp.setContract(token1155Inventory.getTokenAddress());
        queryTokenIdDetailResp.setTokenId(token1155Inventory.getTokenId());
        queryTokenIdDetailResp.setBalance("");
        queryTokenIdDetailResp.setImage(token1155Inventory.getImage());
        queryTokenIdDetailResp.setName(token1155Inventory.getName());
        queryTokenIdDetailResp.setTxCount(token1155Inventory.getTokenTxQty());
        queryTokenIdDetailResp.setTokenName("");
        queryTokenIdDetailResp.setSymbol("");
        return queryTokenIdDetailResp;
    }

    public AccountDownload exportTokenId(String address, String contract, String tokenId, String local, String timeZone) {
        PageHelper.startPage(1, 3000);
        Token1155HolderExample example = new Token1155HolderExample();
        Token1155HolderExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(address)) {
            criteria.andAddressEqualTo(address);
        }
        if (StringUtils.isNotBlank(contract)) {
            criteria.andTokenAddressEqualTo(contract);
        }
        if (StringUtils.isNotBlank(tokenId)) {
            criteria.andTokenIdEqualTo(tokenId);
        }
        Page<Token1155Holder> token1155HolderList = token1155HolderMapper.selectByExample(example);
        String[] headers = {this.i18n.i(I18nEnum.DOWNLOAD_TOKEN_CSV_TOKEN, local), this.i18n.i(I18nEnum.DOWNLOAD_TOKEN_CSV_ADDRESS, local), this.i18n.i(I18nEnum.DOWNLOAD_TOKEN_CSV_TOKEN_ID,
                                                                                                                                                        local), this.i18n.i(I18nEnum.DOWNLOAD_TOKEN_CSV_TX_COUNT,
                                                                                                                                                                            local)};
        List<Object[]> rows = new ArrayList<>();
        token1155HolderList.forEach(tokenInventory -> {
            Object[] row = {tokenInventory.getTokenAddress(), tokenInventory.getAddress(), tokenInventory.getTokenId(), tokenInventory.getTokenOwnerTxQty()};
            rows.add(row);
        });
        return this.downFileCommon.writeDate("Token-Id-" + address + "-" + System.currentTimeMillis() + ".CSV", rows, headers);

    }

}
