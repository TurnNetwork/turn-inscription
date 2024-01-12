package com.turn.inscription.dao.mapper;

import com.turn.inscription.dao.entity.AddrGame;
import com.turn.inscription.dao.entity.AddrGameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AddrGameMapper {
    long countByExample(AddrGameExample example);

    int deleteByExample(AddrGameExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AddrGame record);

    int insertSelective(AddrGame record);

    List<AddrGame> selectByExample(AddrGameExample example);

    AddrGame selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AddrGame record, @Param("example") AddrGameExample example);

    int updateByExample(@Param("record") AddrGame record, @Param("example") AddrGameExample example);

    int updateByPrimaryKeySelective(AddrGame record);

    int updateByPrimaryKey(AddrGame record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table addr_game
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsert(@Param("list") List<AddrGame> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table addr_game
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsertSelective(@Param("list") List<AddrGame> list, @Param("selective") AddrGame.Column ... selective);

    void endGame(@Param("roundId") Long roundId, @Param("gameId") Long gameId);
}