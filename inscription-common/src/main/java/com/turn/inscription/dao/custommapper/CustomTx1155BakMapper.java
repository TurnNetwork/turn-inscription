package com.turn.inscription.dao.custommapper;

import com.turn.inscription.elasticsearch.dto.ErcTx;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface CustomTx1155BakMapper {

    int batchInsert(@Param("set") Set<ErcTx> set);

    long findMaxId();
}
