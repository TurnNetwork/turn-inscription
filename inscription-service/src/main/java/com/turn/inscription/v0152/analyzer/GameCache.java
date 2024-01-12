package com.turn.inscription.v0152.analyzer;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.turn.inscription.dao.entity.Game;
import com.turn.inscription.dao.mapper.GameMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class GameCache {

    Map<String, Game> gameMapCache = new ConcurrentHashMap<>();

    Set<String> gameAddressCache = new ConcurrentHashSet<>();

    @Resource
    private GameMapper gameMapper;

    /**
     * Initialize the game address to cache
     */
    public void init() {
        log.info("Initialize the game address to cache");
        List<Game> games = gameMapper.selectByExample(null);
        games.forEach(game -> {
            gameAddressCache.add(game.getContractAddress());
            gameMapCache.put(game.getContractAddress(), game);
        });
    }
    public Collection<String> getGameAddressCache() {
        return Collections.unmodifiableCollection(gameAddressCache);
    }

}
