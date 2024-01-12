package com.turn.inscription.service;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.turn.inscription.dao.entity.*;
import com.turn.inscription.dao.mapper.BubbleMapper;
import com.turn.inscription.enums.BubbleStatusEnum;
import com.turn.inscription.request.bubble.BubbleListReq;
import com.turn.inscription.response.RespPage;
import com.turn.inscription.response.bubble.BubbleListResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;


@Service
public class BubbleService {

    private final Logger logger = LoggerFactory.getLogger(BubbleService.class);

    @Resource
    private BubbleMapper bubbleMapper;


    public RespPage<BubbleListResp> bubbleList(BubbleListReq req) {
        PageHelper.startPage(req.getPageNo(), req.getPageSize());
        Integer status = BubbleStatusEnum.getEnumByName(req.getQueryStatus()).getCode();


        RespPage<BubbleListResp> respPage = new RespPage<>();
        List<BubbleListResp> lists = new LinkedList<>();

        BubbleExample bubbleExample = new BubbleExample();
        bubbleExample.setOrderByClause(" create_time desc");
        BubbleExample.Criteria criteria1 = bubbleExample.createCriteria();
        if(!BubbleStatusEnum.ALL.getCode().equals(status)){
            criteria1.andStatusEqualTo(status);
        }
        if (ObjectUtil.isNotNull(req.getBubbleId())) {
            criteria1.andBubbleIdEqualTo(req.getBubbleId());
        }

        Page<Bubble> bubblePage = bubbleMapper.selectListByExample(bubbleExample);
        List<Bubble> bubbleList = bubblePage.getResult();

        for (Bubble bubble : bubbleList) {
            BubbleListResp bubbleListResp = new BubbleListResp();
            BeanUtils.copyProperties(bubble, bubbleListResp);
            lists.add(bubbleListResp);
        }
        Page<?> page = new Page<>(req.getPageNo(), req.getPageSize());
        page.setTotal(bubblePage.getTotal());
        respPage.init(page, lists);
        return respPage;
    }

}
