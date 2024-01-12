package com.turn.inscription.task;

import com.turn.inscription.AgentTestData;
import com.turn.inscription.enums.AppStatus;
import com.turn.inscription.utils.AppStatusUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @description:
 **/
@RunWith(MockitoJUnitRunner.Silent.class)
public class RingBufferMonitorTaskTest extends AgentTestData {
    @InjectMocks
    @Spy
    private RingBufferMonitorTask target;

    @Before
    public void setup() {
    }

    @Test
    public void test() {
        AppStatusUtil.setStatus(AppStatus.RUNNING);
        target.ringBufferMonitor();
        verify(target, times(1)).ringBufferMonitor();
    }
}
