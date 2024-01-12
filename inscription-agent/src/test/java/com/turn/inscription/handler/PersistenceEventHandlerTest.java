package com.turn.inscription.handler;

import com.turn.inscription.AgentTestBase;
import com.turn.inscription.cache.NetworkStatCache;
import com.turn.inscription.service.elasticsearch.EsImportService;
import com.turn.inscription.service.redis.RedisImportService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

/**
 * @description: MySQL/ES/Redis启动一致性自检服务测试
 **/
//@RunWith(MockitoJUnitRunner.Silent.class)
public class PersistenceEventHandlerTest extends AgentTestBase {

    @Mock
    private EsImportService esImportService;

    @Mock
    private RedisImportService redisImportService;

    @Mock
    private NetworkStatCache networkStatCache;

    @InjectMocks
    @Spy
    private PersistenceEventHandler target;

    @Before
    public void setup() throws Exception {

    }

}
