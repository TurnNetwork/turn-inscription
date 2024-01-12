package com.turn.inscription.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.turn.inscription.dao.custommapper.CustomInscriptionHolderMapper;
import com.turn.inscription.dao.custommapper.CustomInscriptionInventoryMapper;
import com.turn.inscription.dao.custommapper.CustomInscriptionMapper;
import com.turn.inscription.dao.entity.*;
import com.turn.inscription.enums.*;
import com.turn.inscription.request.inscription.*;
import com.turn.inscription.response.BaseResp;
import com.turn.inscription.response.RespPage;
import com.turn.inscription.response.inscription.*;
import com.turn.inscription.utils.I18nUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Inscription records within the contract
 */
@Service
@Slf4j
public class InscriptionService {

    @Resource
    private I18nUtil i18n;

    @Resource
    private CustomInscriptionMapper customInscriptionMapper;
    @Resource
    private CustomInscriptionHolderMapper customInscriptionHolderMapper;

    @Resource
    private CustomInscriptionInventoryMapper customInscriptionInventoryMapper;


    public RespPage<InscriptionListResp> list(InscriptionListReq req) {
        PageHelper.startPage(req.getPageNo(), req.getPageSize());
        Integer status = InscriptionStatusEnum.getEnumByName(req.getQueryStatus()).getCode();


        RespPage<InscriptionListResp> respPage = new RespPage<>();
        List<InscriptionListResp> lists = new LinkedList<>();
        /** Query list based on conditions and status */
        InscriptionExample inscriptionExample = new InscriptionExample();
        inscriptionExample.setOrderByClause(" create_time desc");
        InscriptionExample.Criteria criteria1 = inscriptionExample.createCriteria();
        if(!InscriptionStatusEnum.ALL.getCode().equals(status)){
            criteria1.andStatusEqualTo(status);
        }
        if (StringUtils.isNotBlank(req.getTick())) {
            criteria1.andTickLike("%" + req.getTick() + "%");
            inscriptionExample.or(criteria1);
        }

        if (StringUtils.isNotBlank(req.getDeployBy())) {
            criteria1.andDeployByEqualTo(req.getDeployBy());
            inscriptionExample.or(criteria1);
        }

        Page<Inscription> inscriptionPage = customInscriptionMapper.selectListByExample(inscriptionExample);
        List<Inscription> inscriptionList = inscriptionPage.getResult();

        for (Inscription inscription : inscriptionList) {
            InscriptionListResp inscriptionListResp = new InscriptionListResp();
            BeanUtils.copyProperties(inscription, inscriptionListResp);
            inscriptionListResp.setProgress(BigDecimal.valueOf(inscription.getMinted()).divide(BigDecimal.valueOf(inscription.getTotalSupply()),4,RoundingMode.DOWN).multiply(new BigDecimal("100")));
            lists.add(inscriptionListResp);
        }
        Page<?> page = new Page<>(req.getPageNo(), req.getPageSize());
        page.setTotal(inscriptionPage.getTotal());
        respPage.init(page, lists);
        return respPage;
    }

    public BaseResp<InscriptionDetailsResp> details(InscriptionDetailsReq req) {
        Inscription inscription = customInscriptionMapper.selectByInscriptionId(req.getInscriptionId());
        InscriptionDetailsResp inscriptionDetailsResp = new InscriptionDetailsResp();
        if(ObjectUtil.isNotNull(inscription)){
            BeanUtils.copyProperties(inscription,inscriptionDetailsResp);
            inscriptionDetailsResp.setProgress(BigDecimal.valueOf(inscription.getMinted()).divide(BigDecimal.valueOf(inscription.getTotalSupply()),4,RoundingMode.DOWN).multiply(new BigDecimal("100")));
        }
        return BaseResp.build(RetEnum.RET_SUCCESS.getCode(), i18n.i(I18nEnum.SUCCESS),inscriptionDetailsResp);
    }

    public RespPage<InscriptionHolderListResp> holderList(InscriptionHolderListReq req) {
        PageHelper.startPage(req.getPageNo(), req.getPageSize());

        RespPage<InscriptionHolderListResp> respPage = new RespPage<>();
        List<InscriptionHolderListResp> lists = new LinkedList<>();
        /** Query list based on conditions and status */
        InscriptionHolderExample inscriptionHolderExample = new InscriptionHolderExample();
        if(ObjectUtil.isNotNull(req.getSort())){
            inscriptionHolderExample.setOrderByClause(" balance asc");
        }else {
            inscriptionHolderExample.setOrderByClause(" balance desc");
        }
        InscriptionHolderExample.Criteria criteria = inscriptionHolderExample.createCriteria();

        criteria.andInscriptionIdEqualTo(req.getInscriptionId());

        Page<InscriptionHolder> inscriptionHolderPage = customInscriptionHolderMapper.selectListByExample(inscriptionHolderExample);
        List<InscriptionHolder> inscriptionHolderList = inscriptionHolderPage.getResult();

        int i = (req.getPageNo() - 1) * req.getPageSize();
        for (InscriptionHolder inscriptionHolder : inscriptionHolderList) {
            InscriptionHolderListResp inscriptionHolderListResp = new InscriptionHolderListResp();
            BeanUtils.copyProperties(inscriptionHolder, inscriptionHolderListResp);
            inscriptionHolderListResp.setRank(i+1);
            inscriptionHolderListResp.setBalance(new BigDecimal(inscriptionHolder.getBalance()));
            lists.add(inscriptionHolderListResp);
            i++;
        }
        Page<?> page = new Page<>(req.getPageNo(), req.getPageSize());
        page.setTotal(inscriptionHolderPage.getTotal());
        respPage.init(page, lists);
        return respPage;
    }

    public RespPage<InscriptionInventoryListResp> inventoryList(InscriptionInventoryListReq req) {
        PageHelper.startPage(req.getPageNo(), req.getPageSize());

        RespPage<InscriptionInventoryListResp> respPage = new RespPage<>();
        List<InscriptionInventoryListResp> lists = new LinkedList<>();
        /** Query list based on conditions and status */
        InscriptionInventoryExample inscriptionInventoryExample = new InscriptionInventoryExample();
        inscriptionInventoryExample.setOrderByClause(" create_time desc");
        InscriptionInventoryExample.Criteria criteria = inscriptionInventoryExample.createCriteria();

        criteria.andOwnerEqualTo(req.getAddress());
        criteria.andInscriptionIdEqualTo(req.getInscriptionId());

        if(StrUtil.isNotEmpty(req.getKey())){
            criteria.andExtLike("%" + req.getKey() + "%");
            inscriptionInventoryExample.or(criteria);
            InscriptionInventoryExample.Criteria criteria1 = inscriptionInventoryExample.createCriteria();
            criteria1.andNumEqualTo("%" + req.getKey() + "%");
        }
        Page<InscriptionInventory> inscriptionInventoryPage = customInscriptionInventoryMapper.selectListByExample(inscriptionInventoryExample);
        List<InscriptionInventory> inscriptionInventoryList = inscriptionInventoryPage.getResult();

        for (InscriptionInventory inscriptionInventory : inscriptionInventoryList) {
            InscriptionInventoryListResp inscriptionInventoryListResp = new InscriptionInventoryListResp();
            BeanUtils.copyProperties(inscriptionInventory, inscriptionInventoryListResp);
            lists.add(inscriptionInventoryListResp);
        }
        Page<?> page = new Page<>(req.getPageNo(), req.getPageSize());
        page.setTotal(inscriptionInventoryPage.getTotal());
        respPage.init(page, lists);
        return respPage;
    }

    public RespPage<InscriptionHolderResp> holder(InscriptionHolderReq req) {
        PageHelper.startPage(req.getPageNo(), req.getPageSize());

        RespPage<InscriptionHolderResp> respPage = new RespPage<>();
        List<InscriptionHolderResp> lists = new LinkedList<>();
        /** Query list based on conditions and status */
        InscriptionHolderExample inscriptionHolderExample = new InscriptionHolderExample();
        inscriptionHolderExample.setOrderByClause(" create_time desc");
        InscriptionHolderExample.Criteria criteria = inscriptionHolderExample.createCriteria();

        criteria.andAddressEqualTo(req.getAddress());

        Page<InscriptionHolder> inscriptionHolderPage = customInscriptionHolderMapper.selectListByExample(inscriptionHolderExample);
        List<InscriptionHolder> inscriptionHolderList = inscriptionHolderPage.getResult();

        for (InscriptionHolder inscriptionHolder : inscriptionHolderList) {
            InscriptionHolderResp inscriptionHolderResp = new InscriptionHolderResp();
            BeanUtils.copyProperties(inscriptionHolder, inscriptionHolderResp);
            inscriptionHolderResp.setBalance(new BigDecimal(inscriptionHolder.getBalance()));
            lists.add(inscriptionHolderResp);
        }
        Page<?> page = new Page<>(req.getPageNo(), req.getPageSize());
        page.setTotal(inscriptionHolderPage.getTotal());
        respPage.init(page, lists);
        return respPage;
    }
}
