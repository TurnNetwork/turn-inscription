package com.turn.inscription.task;

import com.turn.inscription.AgentTestData;
import com.turn.inscription.client.TurnClient;
import com.turn.inscription.enums.AppStatus;
import com.turn.inscription.utils.AppStatusUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @description:
 **/
@RunWith(MockitoJUnitRunner.Silent.class)
public class TurnClientMonitorTaskTest extends AgentTestData {
    @Mock
    private TurnClient turnClient;
    @InjectMocks
    @Spy
    private TurnClientMonitorTask target;

    @Before
    public void setup() {

    }

    @Test
    public void test() {
        AppStatusUtil.setStatus(AppStatus.RUNNING);
        target.turnClientMonitor();
        verify(target, times(1)).turnClientMonitor();

        doThrow(new RuntimeException("")).when(turnClient).updateCurrentWeb3jWrapper();
        target.turnClientMonitor();
    }
}
