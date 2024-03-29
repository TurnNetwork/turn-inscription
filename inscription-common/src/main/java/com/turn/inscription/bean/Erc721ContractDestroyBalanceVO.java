package com.turn.inscription.bean;

import lombok.Data;

@Data
public class Erc721ContractDestroyBalanceVO {

    /**
     *Contract
     */
    private String tokenAddress;

    /**
     * Holder
     */
    private String owner;

    /**
     * quantity
     */
    private Integer num;

}
