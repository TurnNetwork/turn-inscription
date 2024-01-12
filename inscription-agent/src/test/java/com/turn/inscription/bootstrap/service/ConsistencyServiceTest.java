package com.turn.inscription.bootstrap.service;

import com.turn.inscription.AgentTestBase;
import com.turn.inscription.bean.CollectionNetworkStat;
import com.turn.inscription.bootstrap.BootstrapEventPublisher;
import com.turn.inscription.bootstrap.ShutdownCallback;
import com.turn.inscription.dao.entity.NetworkStat;
import com.turn.inscription.dao.mapper.NetworkStatMapper;
import com.turn.inscription.service.block.BlockService;
import com.turn.inscription.service.elasticsearch.EsBlockRepository;
import com.turn.inscription.service.elasticsearch.bean.TokenTxSummary;
import com.turn.inscription.service.receipt.ReceiptService;
import com.turn.inscription.utils.CommonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * @description: MySQL/ES/Redis启动一致性自检服务测试
 **/
@RunWith(MockitoJUnitRunner.Silent.class)
public class ConsistencyServiceTest extends AgentTestBase {

    @Mock
    private NetworkStatMapper networkStatMapper;

    @Mock
    private EsBlockRepository ESBlockRepository;

    @Mock
    private BlockService blockService;

    @Mock
    private ReceiptService receiptService;

    @Mock
    private BootstrapEventPublisher bootstrapEventPublisher;

    @Mock
    private ShutdownCallback shutdownCallback;

    @InjectMocks
    @Spy
    private ConsistencyService target;

    @Before
    public void setup() {
        when(shutdownCallback.isDone()).thenReturn(true);
    }

    @Test
    public void post() throws Exception {

        TokenTxSummary summary = new TokenTxSummary();
        //when(oldEsErc20TxRepository.groupContractTxCount()).thenReturn(summary);

        NetworkStat networkStat = null;
        when(networkStatMapper.selectByPrimaryKey(anyInt())).thenReturn(networkStat);
        String traceId = CommonUtil.createTraceId();
        target.post(traceId);

        networkStat = CollectionNetworkStat.newInstance();
        networkStat.setCurNumber(10L);
        when(networkStatMapper.selectByPrimaryKey(anyInt())).thenReturn(networkStat);
        when(ESBlockRepository.exists(anyString())).thenReturn(false);
        target.post(traceId);
        when(ESBlockRepository.exists(anyString())).thenReturn(true);
        target.post(traceId);

        when(blockService.getBlockAsync(anyLong())).thenReturn(null);
        when(receiptService.getReceiptAsync(anyLong())).thenReturn(null);
        target.post(traceId);

        verify(target, times(4)).post(traceId);
    }

}
