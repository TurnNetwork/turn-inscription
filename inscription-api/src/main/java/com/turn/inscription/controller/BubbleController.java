package com.turn.inscription.controller;

import com.turn.inscription.request.bubble.BubbleListReq;
import com.turn.inscription.response.RespPage;
import com.turn.inscription.response.bubble.BubbleListResp;
import com.turn.inscription.service.BubbleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * bubble controller
 */
@Slf4j
@RestController
public class BubbleController {

    @Resource
    private BubbleService bubbleService;


    @PostMapping("bubble/list")
    public Mono<RespPage<BubbleListResp>> bubbleList(@Valid @RequestBody BubbleListReq req) {
        return Mono.just(bubbleService.bubbleList(req));
    }

}
