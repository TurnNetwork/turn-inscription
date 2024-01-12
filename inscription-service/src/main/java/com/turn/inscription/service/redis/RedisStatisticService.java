package com.turn.inscription.service.redis;

import com.turn.inscription.dao.entity.NetworkStat;
import org.springframework.stereotype.Service;

@Service
public class RedisStatisticService extends AbstractRedisService<NetworkStat> {
    @Override
    public String getCacheKey() {
        return redisKeyConfig.getNetworkStat();
    }
}
