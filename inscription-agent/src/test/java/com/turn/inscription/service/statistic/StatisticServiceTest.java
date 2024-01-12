package com.turn.inscription.service.statistic;

import com.turn.inscription.AgentTestBase;
import com.turn.inscription.bean.EpochMessage;
import com.turn.inscription.cache.AddressCache;
import com.turn.inscription.bean.CollectionEvent;
import com.turn.inscription.analyzer.statistic.StatisticsAddressAnalyzer;
import com.turn.inscription.analyzer.statistic.StatisticsNetworkAnalyzer;
import com.turn.inscription.elasticsearch.dto.Block;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @description: MySQL/ES/Redis启动一致性自检服务测试
 **/
@RunWith(MockitoJUnitRunner.Silent.class)
public class StatisticServiceTest extends AgentTestBase {
    @Mock
    private AddressCache addressCache;
    @Mock
    private StatisticsNetworkAnalyzer statisticsNetworkAnalyzer;
    @Mock
    private StatisticsAddressAnalyzer statisticsAddressAnalyzer;
    @InjectMocks
    @Spy
    private StatisticService target;

    @Before
    public void setup() throws Exception {
        when(addressCache.getAll()).thenReturn(new ArrayList<>(addressList));
    }

    @Test
    public void test() throws Exception {
        Block block = blockList.get(0);
        EpochMessage epochMessage=EpochMessage.newInstance();
        CollectionEvent event = new CollectionEvent();
        event.setBlock(blockList.get(0));
        event.setEpochMessage(EpochMessage.newInstance());
        event.setTransactions(new ArrayList <>(transactionList));
        target.analyze(event);
        verify(target, times(1)).analyze(any());
    }
}
