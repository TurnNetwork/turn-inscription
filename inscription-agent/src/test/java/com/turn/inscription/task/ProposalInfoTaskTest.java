package com.turn.inscription.task;

import com.turn.inscription.AgentTestData;
import com.turn.inscription.bean.CollectionNetworkStat;
import com.turn.inscription.cache.NetworkStatCache;
import com.turn.inscription.enums.AppStatus;
import com.turn.inscription.service.proposal.ProposalService;
import com.turn.inscription.utils.AppStatusUtil;
import com.turn.inscription.dao.entity.NetworkStat;
import com.turn.inscription.dao.custommapper.CustomProposalMapper;
import com.turn.inscription.dao.mapper.ProposalMapper;
import com.turn.inscription.exception.BlankResponseException;
import com.turn.inscription.exception.ContractInvokeException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

/**
 * @description:
 **/
@RunWith(MockitoJUnitRunner.Silent.class)
public class ProposalInfoTaskTest extends AgentTestData {
    @Mock
    private NetworkStatCache networkStatCache;
    @Mock
    private ProposalMapper proposalMapper;
    @Mock
    private CustomProposalMapper customProposalMapper;
    @Mock
    private ProposalService proposalService;


    @Before
    public void setup() throws IOException {
//        ReflectionTestUtils.setField(target, "turnClient", turnClient);
//        Web3jWrapper ww = mock(Web3jWrapper.class);
//        when(turnClient.getWeb3jWrapper()).thenReturn(ww);
//        Web3j web3j = mock(Web3j.class);
//        when(ww.getWeb3j()).thenReturn(web3j);
//        Request rq = mock(Request.class);
//        when(web3j.turnBlockNumber()).thenReturn(rq);
//        TurnBlockNumber rs = mock(TurnBlockNumber.class);
//        when(rq.send()).thenReturn(rs);
//        when(rs.getBlockNumber()).thenReturn(BigInteger.TEN);
    }

    @Test
    public void test() throws ContractInvokeException, BlankResponseException {
        AppStatusUtil.setStatus(AppStatus.RUNNING);
        when(proposalMapper.selectByExample(any())).thenReturn(new ArrayList<>(proposalList));
        NetworkStat net = CollectionNetworkStat.newInstance();
        net.setCurNumber(100000L);
        when(networkStatCache.getNetworkStat()).thenReturn(net);
    }
}
