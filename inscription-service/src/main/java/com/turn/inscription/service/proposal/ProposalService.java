package com.turn.inscription.service.proposal;

import com.turn.inscription.bean.ProposalParticipantStat;
import com.turn.inscription.client.TurnClient;
import com.turn.inscription.client.SpecialApi;
import com.turn.inscription.exception.BlankResponseException;
import com.turn.inscription.exception.ContractInvokeException;
import com.bubble.contracts.dpos.dto.CallResponse;
import com.bubble.contracts.dpos.dto.resp.TallyResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: Proposal service
 **/
@Slf4j
@Service
public class ProposalService {

    @Resource
    private TurnClient turnClient;

    @Resource
    private SpecialApi specialApi;

    /**
     * Get statistics of proposal participants
     *
     * @param proposalHash
     * @param blockHash
     * @return
     * @throwsException
     */
    public ProposalParticipantStat getProposalParticipantStat(String proposalHash, String blockHash) throws ContractInvokeException, BlankResponseException {
        return specialApi.getProposalParticipants(turnClient.getWeb3jWrapper().getWeb3j(), proposalHash, blockHash);
    }

    /**
     * Get the proposal voting results based on the proposal hash
     *
     * @param proposalHash
     * @return
     * @throwsException
     */
    public TallyResult getTallyResult(String proposalHash) throws Exception {
        CallResponse<TallyResult> result = turnClient.getProposalContract().getTallyResult(proposalHash).send();
        if (result.getData() == null) {
            log.warn("The voting result of proposal[" + proposalHash + "] is empty!");
        }
        return result.getData();
    }

}