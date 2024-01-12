package com.turn.inscription.dao.mapper;

import com.turn.inscription.dao.entity.TxInscriptionBak;
import com.turn.inscription.dao.entity.TxInscriptionBakExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TxInscriptionBakMapper {
    long countByExample(TxInscriptionBakExample example);

    int deleteByExample(TxInscriptionBakExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TxInscriptionBak record);

    int insertSelective(TxInscriptionBak record);

    List<TxInscriptionBak> selectByExampleWithBLOBs(TxInscriptionBakExample example);

    List<TxInscriptionBak> selectByExample(TxInscriptionBakExample example);

    TxInscriptionBak selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TxInscriptionBak record, @Param("example") TxInscriptionBakExample example);

    int updateByExampleWithBLOBs(@Param("record") TxInscriptionBak record, @Param("example") TxInscriptionBakExample example);

    int updateByExample(@Param("record") TxInscriptionBak record, @Param("example") TxInscriptionBakExample example);

    int updateByPrimaryKeySelective(TxInscriptionBak record);

    int updateByPrimaryKeyWithBLOBs(TxInscriptionBak record);

    int updateByPrimaryKey(TxInscriptionBak record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_inscription_bak
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsert(@Param("list") List<TxInscriptionBak> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_inscription_bak
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsertSelective(@Param("list") List<TxInscriptionBak> list, @Param("selective") TxInscriptionBak.Column ... selective);
}