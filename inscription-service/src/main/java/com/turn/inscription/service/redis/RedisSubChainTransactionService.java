package com.turn.inscription.service.redis;

import com.alibaba.fastjson.JSON;
import com.turn.inscription.bean.SubChainTx;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

/**
 * Sub Chain transaction cache data processing logic
 */
@Service
public class RedisSubChainTransactionService extends AbstractRedisService<SubChainTx> {
    @Override
    public String getCacheKey() {
        return redisKeyConfig.getSubChainTransaction();
    }

    @Override
    public void updateMinMaxScore(Set<SubChainTx> data) {
        minMax.reset();
        data.forEach(item->{
            Long score = item.getSeq();
            if(score<minMax.getMinOffset()) minMax.setMinOffset(score);
            if(score>minMax.getMaxOffset()) minMax.setMaxOffset(score);
        });
    }

    @Override
    public void updateExistScore(Set<String> exist) {
        Objects.requireNonNull(exist).forEach(item->existScore.add(JSON.parseObject(item, SubChainTx.class).getSeq()));
    }

    @Override
    public void updateStageSet(Set<SubChainTx> data) {
        data.forEach(item -> {
            // Only put into the cache if they do not exist in the cache
            if(!existScore.contains(item.getSeq())) stageSet.add(new DefaultTypedTuple<String>(JSON.toJSONString(item), (double) item.getSeq()));
        });
    }
}