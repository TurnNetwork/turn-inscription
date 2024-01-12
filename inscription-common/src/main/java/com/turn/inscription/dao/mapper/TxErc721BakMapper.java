package com.turn.inscription.dao.mapper;

import com.turn.inscription.dao.entity.TxErc721Bak;
import com.turn.inscription.dao.entity.TxErc721BakExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TxErc721BakMapper {
    long countByExample(TxErc721BakExample example);

    int deleteByExample(TxErc721BakExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TxErc721Bak record);

    int insertSelective(TxErc721Bak record);

    List<TxErc721Bak> selectByExampleWithBLOBs(TxErc721BakExample example);

    List<TxErc721Bak> selectByExample(TxErc721BakExample example);

    TxErc721Bak selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TxErc721Bak record, @Param("example") TxErc721BakExample example);

    int updateByExampleWithBLOBs(@Param("record") TxErc721Bak record, @Param("example") TxErc721BakExample example);

    int updateByExample(@Param("record") TxErc721Bak record, @Param("example") TxErc721BakExample example);

    int updateByPrimaryKeySelective(TxErc721Bak record);

    int updateByPrimaryKeyWithBLOBs(TxErc721Bak record);

    int updateByPrimaryKey(TxErc721Bak record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_erc_721_bak
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsert(@Param("list") List<TxErc721Bak> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_erc_721_bak
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsertSelective(@Param("list") List<TxErc721Bak> list, @Param("selective") TxErc721Bak.Column ... selective);
}