package com.turn.inscription.bootstrap.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @description: 初始化结果
 **/
@RunWith(MockitoJUnitRunner.Silent.class)
public class InitializationResultTest {

    @Test
    public void test(){
        InitializationResult initializationResult = new InitializationResult();
        initializationResult.setCollectedBlockNumber(100L);
        initializationResult.getCollectedBlockNumber();
    }
}
