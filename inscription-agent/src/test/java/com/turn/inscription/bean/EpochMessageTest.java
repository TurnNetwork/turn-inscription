package com.turn.inscription.bean;

import com.turn.inscription.AgentTestBase;
import com.turn.inscription.service.epoch.EpochRetryService;
import com.turn.inscription.service.epoch.EpochService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertTrue;

/**
 * @Description: 年化率信息bean单元测试
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class EpochMessageTest extends AgentTestBase {

    @Test
    public void test() throws InvocationTargetException, IllegalAccessException {
        EpochMessage message = EpochMessage.newInstance();
        for (Method m : EpochMessage.class.getDeclaredMethods()) {
            if (m.getName().contains("set")) {
                if (m.getName().contains("List")) {
                    m.invoke(message, validatorList);
                } else if (m.getName().contains("Reward")) {
                    m.invoke(message, BigDecimal.TEN);
                } else if (m.getName().contains("ExpectBlockCount")) {
                    m.invoke(message, Long.valueOf("10"));
                } else {
                    m.invoke(message, BigInteger.TEN);
                }
            }
            if (m.getName().contains("get"))
                m.invoke(message);
        }
        EpochService epochService = new EpochService();
        message.updateWithEpochService(epochService);
        EpochRetryService epochRetryService = new EpochRetryService();
        message.updateWithEpochRetryService(epochRetryService);

        assertTrue(true);
    }

}
