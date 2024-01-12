package com.turn.inscription.service.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Inscription transaction record ES operation class
 */
@Repository
@Slf4j
public class EsInscriptionTxRepository extends AbstractEsRepository {
    @Override
    public String getIndexName() {
        return config.getInscriptionTxIndexName();
    }
    @Override
    public String getTemplateFileName(){return "inscription-tx";}
}
