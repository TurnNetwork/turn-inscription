package com.turn.inscription.dao.custommapper;

import com.turn.inscription.elasticsearch.dto.ErcTx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomTx20BakMapper {

    int batchInsert(@Param("list") List<ErcTx> list);

    long findMaxId();

}
