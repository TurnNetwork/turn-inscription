package com.turn.inscription.service.block;

import com.turn.inscription.AgentTestBase;
import com.turn.inscription.analyzer.epoch.OnConsensusAnalyzer;
import com.turn.inscription.analyzer.epoch.OnElectionAnalyzer;
import com.turn.inscription.analyzer.epoch.OnNewBlockAnalyzer;
import com.turn.inscription.analyzer.epoch.OnSettleAnalyzer;
import com.turn.inscription.bean.CollectionEvent;
import com.turn.inscription.bean.EpochMessage;
import com.turn.inscription.elasticsearch.dto.Block;
import com.turn.inscription.exception.CollectionBlockException;
import com.turn.inscription.exception.NoSuchBeanException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * @description: MySQL/ES/Redis启动一致性自检服务测试
 **/
@RunWith(MockitoJUnitRunner.Silent.class)
public class BlockServiceTest extends AgentTestBase {
    @Mock
    private BlockRetryService retryService;
    @Mock
    private OnNewBlockAnalyzer onNewBlockAnalyzer;
    @Mock
    private OnElectionAnalyzer onElectionAnalyzer;
    @Mock
    private OnConsensusAnalyzer onConsensusAnalyzer;
    @Mock
    private OnSettleAnalyzer onSettleAnalyzer;
    @InjectMocks
    @Spy
    private BlockService target;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(target, "chainConfig", blockChainConfig);
    }

    @Test
    public void test() throws IOException, CollectionBlockException, NoSuchBeanException {
        target.getBlockAsync(1L);
        when(retryService.getBlock(any())).thenThrow(new RuntimeException(""));
        target.getBlockAsync(1L);

        target.checkBlockNumber(1L);
        //doThrow(new RuntimeException("")).when(retryService).checkBlockNumber(anyLong());
        target.checkBlockNumber(1L);

        when(onElectionAnalyzer.analyze(any(),any())).thenReturn(nodeOptList);

        Block block = blockList.get(0);
        EpochMessage epochMessage=EpochMessage.newInstance();
        CollectionEvent event = new CollectionEvent();
        event.setBlock(blockList.get(0));
        event.setEpochMessage(epochMessage);
        event.setTransactions(new ArrayList<>(transactionList));

        block.setNum(79L);
        epochMessage.setConsensusEpochRound(BigInteger.TEN);
        target.analyze(event);
        block.setNum(81L);
        target.analyze(event);

    }
}
