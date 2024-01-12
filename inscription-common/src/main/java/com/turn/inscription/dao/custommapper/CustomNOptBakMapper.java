package com.turn.inscription.dao.custommapper;

import com.turn.inscription.elasticsearch.dto.NodeOpt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomNOptBakMapper {

    int batchInsertOrUpdateSelective(@Param("list") List<NodeOpt> list);

    /**
     * Get the latest serial number of node operation records
     *
     * @param :
     * @return: long
     */
    long getLastNodeOptSeq();

}