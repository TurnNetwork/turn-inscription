package com.turn.inscription.dao.custommapper;

import com.github.pagehelper.Page;
import com.turn.inscription.bean.*;
import com.turn.inscription.dao.entity.Token;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomTokenMapper {

    Page<CustomToken> selectListByType(@Param("type") String type);

    CustomTokenDetail selectDetailByAddress(@Param("address") String address);

    int batchInsertOrUpdateSelective(@Param("list") List<Token> list, @Param("selective") Token.Column... selective);

    /**
     * Find destroyed contracts
     *
     * @param type: {@link com.turn.inscription.enums.ErcTypeEnum}
     * @return: java.util.List<com.turn.browser.bean.DestroyContract>
     */
    List<DestroyContract> findDestroyContract(@Param("type") String type);

    /**
     * Number of transactions to update tokens in batches
     *
     * @param list:
     * @return: int
     */
    int batchUpdateTokenQty(@Param("list") List<TokenQty> list);

    /**
     * Update total token supply
     *
     * @param list:
     * @return: int
     */
    int batchUpdateTokenTotalSupply(@Param("list") List<ErcToken> list);

    int batchUpdateTokenHolder(@Param("list") List<Token> list);
}