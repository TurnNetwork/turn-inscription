package com.turn.inscription.service;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.turn.inscription.dao.entity.*;
import com.turn.inscription.dao.mapper.MicroNodeMapper;
import com.turn.inscription.enums.*;
import com.turn.inscription.request.micronode.AliveMicroNodeListReq;
import com.turn.inscription.request.micronode.MicroNodeDetailsReq;
import com.turn.inscription.request.micronode.MicroNodeOptRecordListReq;
import com.turn.inscription.response.BaseResp;
import com.turn.inscription.response.RespPage;
import com.turn.inscription.response.microNode.AliveMicroNodeListResp;
import com.turn.inscription.response.microNode.MicroNodeDetailsResp;
import com.turn.inscription.response.microNode.MicroNodeOptRecordListResp;
import com.turn.inscription.response.microNode.MicroNodeStatisticResp;
import com.turn.inscription.service.elasticsearch.EsMicroNodeOptRepository;
import com.turn.inscription.service.elasticsearch.bean.ESResult;
import com.turn.inscription.service.elasticsearch.query.ESQueryBuilderConstructor;
import com.turn.inscription.service.elasticsearch.query.ESQueryBuilders;
import com.turn.inscription.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Micronode module method
 */
@Service
public class MicroNodeService {

    private final Logger logger = LoggerFactory.getLogger(MicroNodeService.class);

    @Resource
    private MicroNodeMapper microNodeMapper;

    @Resource
    private EsMicroNodeOptRepository esMicroNodeOptRepository;

    @Resource
    private I18nUtil i18n;

    public MicroNodeStatisticResp stakingStatistic() {
        /** Get statistics */
        MicroNodeStatisticResp stakingStatisticNewResp = new MicroNodeStatisticResp();
        BigInteger stakingAmount = microNodeMapper.countStakingAmount();
        stakingStatisticNewResp.setStakingValue(ObjectUtil.isNotNull(stakingAmount)?new BigDecimal(stakingAmount):BigDecimal.ZERO);
        return stakingStatisticNewResp;
    }

    public RespPage<AliveMicroNodeListResp> aliveMicroNodeList(AliveMicroNodeListReq req) {
        PageHelper.startPage(req.getPageNo(), req.getPageSize());
        Integer status = MicroNodeStatusEnum.getEnumByName(req.getQueryStatus()).getCode();


        RespPage<AliveMicroNodeListResp> respPage = new RespPage<>();
        List<AliveMicroNodeListResp> lists = new LinkedList<>();
        /** Query list based on conditions and status */
        MicroNodeExample microNodeExample = new MicroNodeExample();
        microNodeExample.setOrderByClause(" create_time desc");
        MicroNodeExample.Criteria criteria1 = microNodeExample.createCriteria();
        if(!MicroNodeStatusEnum.ALL.getCode().equals(status)){
            criteria1.andNodeStatusEqualTo(status);
        }
        if (StringUtils.isNotBlank(req.getKey())) {
            criteria1.andNameLike("%" + req.getKey() + "%");
            microNodeExample.or(criteria1);
            MicroNodeExample.Criteria criteria2 = microNodeExample.createCriteria();
            criteria2.andBubbleCreatorLike("%" + req.getKey() + "%");
            microNodeExample.or().andBubbleCreatorLike("%" + req.getKey() + "%");
        }
        if(ObjectUtil.isNotNull(req.getBubbleId())) {
            microNodeExample.or().andBubbleIdEqualTo(req.getBubbleId());
        }

        Page<MicroNode> microNodePage = microNodeMapper.selectListByExample(microNodeExample);
        List<MicroNode> microNodeList = microNodePage.getResult();

        int i = (req.getPageNo() - 1) * req.getPageSize();
        for (MicroNode microNode : microNodeList) {
            AliveMicroNodeListResp aliveMicroNodeListResp = new AliveMicroNodeListResp();
            BeanUtils.copyProperties(microNode, aliveMicroNodeListResp);
            aliveMicroNodeListResp.setAmount(microNode.getAmount().toPlainString());
            aliveMicroNodeListResp.setRanking(i + 1);
            lists.add(aliveMicroNodeListResp);
            i++;
        }
        Page<?> page = new Page<>(req.getPageNo(), req.getPageSize());
        page.setTotal(microNodePage.getTotal());
        respPage.init(page, lists);
        return respPage;
    }

    public BaseResp<MicroNodeDetailsResp> microNodeDetails(MicroNodeDetailsReq req) {

        MicroNode microNode = microNodeMapper.selectByPrimaryKey(req.getNodeId());
        MicroNodeDetailsResp microNodeDetailsResp = new MicroNodeDetailsResp();
        // There is only one piece of data
        if (microNode != null) {
            BeanUtils.copyProperties(microNode, microNodeDetailsResp);
            microNodeDetailsResp.setTotalValue(microNode.getAmount());
        }
        return BaseResp.build(RetEnum.RET_SUCCESS.getCode(), i18n.i(I18nEnum.SUCCESS), microNodeDetailsResp);
    }

    public RespPage<MicroNodeOptRecordListResp> microNodeOptRecordList(MicroNodeOptRecordListReq req) {
        RespPage<MicroNodeOptRecordListResp> respPage = new RespPage<>();
        ESQueryBuilderConstructor constructor = new ESQueryBuilderConstructor();
        constructor.must(new ESQueryBuilders().term("nodeId", req.getNodeId()));
        ESResult<MicroNodeOptBak> items = new ESResult<>();
        constructor.setDesc("id");
        try {
            items = esMicroNodeOptRepository.search(constructor, MicroNodeOptBak.class, req.getPageNo(), req.getPageSize());
        } catch (Exception e) {
            logger.error("Error in obtaining micronode operation record.", e);
            return respPage;
        }
        List<MicroNodeOptBak> microNodeOptBaks = items.getRsData();
        List<MicroNodeOptRecordListResp> lists = new LinkedList<>();
        for (MicroNodeOptBak microNodeOptBak : microNodeOptBaks) {
            MicroNodeOptRecordListResp microNodeOptRecordListResp = new MicroNodeOptRecordListResp();
            BeanUtils.copyProperties(microNodeOptBak, microNodeOptRecordListResp);
            microNodeOptRecordListResp.setType(String.valueOf(microNodeOptBak.getType()));
            microNodeOptRecordListResp.setTimestamp(microNodeOptBak.getTime().getTime());
            microNodeOptRecordListResp.setBlockNumber(microNodeOptBak.getbNum());
            lists.add(microNodeOptRecordListResp);
        }
        /** Query the total number of pages */
        Page<?> page = new Page<>(req.getPageNo(), req.getPageSize());
        page.setTotal(items.getTotal());
        respPage.init(page, lists);
        return respPage;
    }

}