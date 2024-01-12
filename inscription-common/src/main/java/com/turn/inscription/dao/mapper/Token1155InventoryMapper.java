package com.turn.inscription.dao.mapper;

import com.github.pagehelper.Page;
import com.turn.inscription.dao.entity.Token1155Inventory;
import com.turn.inscription.dao.entity.Token1155InventoryExample;
import com.turn.inscription.dao.entity.Token1155InventoryWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Token1155InventoryMapper {
    long countByExample(Token1155InventoryExample example);

    int deleteByExample(Token1155InventoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Token1155InventoryWithBLOBs record);

    int insertSelective(Token1155InventoryWithBLOBs record);

    Page<Token1155InventoryWithBLOBs> selectByExampleWithBLOBs(Token1155InventoryExample example);

    Page<Token1155Inventory> selectByExample(Token1155InventoryExample example);

    Token1155InventoryWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Token1155InventoryWithBLOBs record, @Param("example") Token1155InventoryExample example);

    int updateByExampleWithBLOBs(@Param("record") Token1155InventoryWithBLOBs record, @Param("example") Token1155InventoryExample example);

    int updateByExample(@Param("record") Token1155Inventory record, @Param("example") Token1155InventoryExample example);

    int updateByPrimaryKeySelective(Token1155InventoryWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(Token1155InventoryWithBLOBs record);

    int updateByPrimaryKey(Token1155Inventory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table token_1155_inventory
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsert(@Param("list") List<Token1155InventoryWithBLOBs> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table token_1155_inventory
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsertSelective(@Param("list") List<Token1155InventoryWithBLOBs> list, @Param("selective") Token1155InventoryWithBLOBs.Column ... selective);
}