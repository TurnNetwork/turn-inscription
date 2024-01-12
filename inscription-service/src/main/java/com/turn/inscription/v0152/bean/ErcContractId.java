package com.turn.inscription.v0152.bean;

import com.turn.inscription.enums.ErcTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Erc20 contract identification
 */
@Data
public class ErcContractId {

    private ErcTypeEnum typeEnum = ErcTypeEnum.UNKNOWN;

    /**
     *Contract name
     */
    private String name;

    /**
     *Contract symbol
     */
    private String symbol;

    /**
     *Contract accuracy
     */
    private Integer decimal;

    /**
     *Total supply
     */
    private BigDecimal totalSupply;

}