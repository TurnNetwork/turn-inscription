package com.turn.inscription.controller;

import com.turn.inscription.request.PageReq;
import com.turn.inscription.request.proposal.ProposalDetailRequest;
import com.turn.inscription.request.proposal.VoteListRequest;
import com.turn.inscription.response.BaseResp;
import com.turn.inscription.response.RespPage;
import com.turn.inscription.response.proposal.ProposalDetailsResp;
import com.turn.inscription.response.proposal.ProposalListResp;
import com.turn.inscription.response.proposal.VoteListResp;
import com.turn.inscription.service.ProposalInfoService;
import com.turn.inscription.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Proposal module Contract. Define usage
 */
@Slf4j
@RestController
public class ProposalController {

    @Resource
    private ProposalInfoService proposalInfoService;

    @Resource
    private VoteService voteService;

    /**
     * Proposal list
     *
     * @param req
     * @return reactor.core.publisher.Mono<com.turn.browser.response.RespPage < com.turn.browser.response.proposal.ProposalListResp>>
     */
    @PostMapping("proposal/proposalList")
    public Mono<RespPage<ProposalListResp>> proposalList(@Valid @RequestBody(required = false) PageReq req) {
        return Mono.just(proposalInfoService.list(req));
    }

    /**
     * Proposal details
     *
     * @param req
     * @return reactor.core.publisher.Mono<com.turn.browser.response.BaseResp < com.turn.browser.response.proposal.ProposalDetailsResp>>
     */
    @PostMapping("proposal/proposalDetails")
    public Mono<BaseResp<ProposalDetailsResp>> proposalDetails(@Valid @RequestBody ProposalDetailRequest req) {
        return Mono.just(proposalInfoService.get(req));
    }

    /**
     * voting list
     *
     * @param req
     * @return reactor.core.publisher.Mono<com.turn.browser.response.RespPage < com.turn.browser.response.proposal.VoteListResp>>
     */
    @PostMapping("proposal/voteList")
    public Mono<RespPage<VoteListResp>> voteList(@Valid @RequestBody VoteListRequest req) {
        return Mono.just(voteService.queryByProposal(req));
    }

}
