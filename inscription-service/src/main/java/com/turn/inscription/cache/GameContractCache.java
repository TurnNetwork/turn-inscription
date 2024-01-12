package com.turn.inscription.cache;

import com.bubble.abi.solidity.EventEncoder;
import com.turn.inscription.contract.GameContract;
import com.turn.inscription.enums.GameEventTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class GameContractCache {

    public static final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap();

    @PostConstruct
    public void initCache() {
        cache.put(EventEncoder.encode(GameContract.CREATE_GAME_EVENT),
                GameEventTypeEnum.CREATE_GAME_EVENT.getDesc());
        cache.put(EventEncoder.encode(GameContract.JOIN_GAME_EVENT),
                GameEventTypeEnum.JOIN_GAME_EVENT.getDesc());
        cache.put(EventEncoder.encode(GameContract.END_GAME_EVENT),
                GameEventTypeEnum.END_GAME_EVENT.getDesc());
    }

}
