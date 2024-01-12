package com.turn.inscription.cache;

import com.bubble.abi.solidity.EventEncoder;
import com.turn.inscription.contract.GameContract;
import com.turn.inscription.contract.InscriptionContract;
import com.turn.inscription.enums.GameEventTypeEnum;
import com.turn.inscription.enums.InscriptionEventTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class InscriptionContractCache {

    public static final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap();

    @PostConstruct
    public void initCache() {
        cache.put(EventEncoder.encode(InscriptionContract.MINT_EVENT),
                InscriptionEventTypeEnum.MINT_EVENT.getDesc());
        cache.put(EventEncoder.encode(InscriptionContract.BATCHTRANSFER_EVENT),
                InscriptionEventTypeEnum.BATCH_TRANSFER_EVENT.getDesc());
        cache.put(EventEncoder.encode(InscriptionContract.DEPLOY_EVENT),
                InscriptionEventTypeEnum.DEPLOY_EVENT.getDesc());
    }

}
