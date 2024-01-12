package com.turn.inscription.dao.custommapper;

import com.turn.inscription.dao.param.BusinessParam;
import org.springframework.transaction.annotation.Transactional;


public interface NewBlockMapper {
    /**
     * New block related data updates
     */
    @Transactional(rollbackFor = {Exception.class, Error.class})
    void newBlock(BusinessParam param);
}