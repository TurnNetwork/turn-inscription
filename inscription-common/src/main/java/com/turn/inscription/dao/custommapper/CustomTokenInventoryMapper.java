package com.turn.inscription.dao.custommapper;

import com.turn.inscription.bean.CustomTokenInventory;
import com.turn.inscription.bean.Erc721ContractDestroyBalanceVO;
import com.turn.inscription.dao.entity.TokenInventory;
import com.turn.inscription.dao.entity.TokenInventoryKey;
import com.turn.inscription.dao.entity.TokenInventoryWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomTokenInventoryMapper {

    int batchInsertOrUpdateSelective(@Param("list") List<TokenInventoryWithBLOBs> list, @Param("selective") TokenInventory.Column... selective);

    void burnAndDelTokenInventory(@Param("list") List<TokenInventoryKey> list);

    CustomTokenInventory selectTokenInventory(TokenInventoryKey key);

    List<Erc721ContractDestroyBalanceVO> findErc721ContractDestroyBalance(@Param("tokenAddress") String tokenAddress);

    void batchUpdateTokenInfo(@Param("list") List<TokenInventoryWithBLOBs> list);

    long findMaxId();

}