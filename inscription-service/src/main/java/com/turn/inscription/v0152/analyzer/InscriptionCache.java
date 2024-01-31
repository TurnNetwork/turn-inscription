package com.turn.inscription.v0152.analyzer;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.turn.inscription.dao.entity.Inscription;
import com.turn.inscription.dao.mapper.InscriptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class InscriptionCache {

    Map<String, Inscription> inscriptionMapCache = new ConcurrentHashMap<>();

    Set<String> inscriptionAddrCache = new ConcurrentHashSet<>();

    @Resource
    private InscriptionMapper inscriptionMapper;

    /**
     * Initialize the game address to cache
     */
    public void init() {
        log.info("Initialize the game address to cache");
        List<Inscription> inscriptions = inscriptionMapper.selectByExample(null);
        inscriptions.forEach(inscription -> {
            inscriptionAddrCache.add(inscription.getContractAddress());
            inscriptionMapCache.put(inscription.getContractAddress(), inscription);
        });
    }
    public Collection<String> getInscriptionCache() {
        return Collections.unmodifiableCollection(inscriptionAddrCache);
    }

}
