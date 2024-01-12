package com.turn.inscription.service.elasticsearch;

import org.springframework.stereotype.Repository;


@Repository
public class EsSubChainTxRepository extends AbstractEsRepository {
    @Override
    public String getIndexName() {
        return config.getSubChainTxIndexName();
    }
    @Override
    public String getTemplateFileName() {
        return "subChainTx";
    }
}
