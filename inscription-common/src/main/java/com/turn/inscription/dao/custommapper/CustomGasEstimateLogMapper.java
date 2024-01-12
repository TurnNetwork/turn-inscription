package com.turn.inscription.dao.custommapper;

import com.turn.inscription.dao.entity.GasEstimateLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomGasEstimateLogMapper {
    int batchInsertOrUpdateSelective(@Param("list") List<GasEstimateLog> list, @Param("selective") GasEstimateLog.Column... selective);
}