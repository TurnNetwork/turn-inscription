package com.turn.inscription.dao.custommapper;

import com.turn.inscription.dao.entity.BlockNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: BlockNodeBusinessMapper.java
 * @description:
 */
public interface CustomBlockNodeMapper {
    int batchInsert(@Param("list") List<BlockNode> list);

    int selectMaxNum();

    List<BlockNode> selectNodeByDis(@Param("startNum") Integer startNum, @Param("endNum") Integer endNum);
}