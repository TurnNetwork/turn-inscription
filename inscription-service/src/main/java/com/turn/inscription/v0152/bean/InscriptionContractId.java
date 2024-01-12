package com.turn.inscription.v0152.bean;

import com.turn.inscription.enums.InscriptionTypeEnum;
import lombok.Data;


/**
 * inscription Contract ID
 */
@Data
public class InscriptionContractId {

    private InscriptionTypeEnum typeEnum = InscriptionTypeEnum.UNKNOWN;

    private String inscriptionId;

    private String tick;

    private Long totalSupply;

    private Long limitPerMint;

    private Long maxPerWallet;

    private String extendValue;

    private String deployBy;

    private Long deployTime;

}
