package com.turn.inscription.v0152.bean;

import com.turn.inscription.enums.GameTypeEnum;
import lombok.Data;


/**
 * game Contract ID
 */
@Data
public class GameContractId {

    private GameTypeEnum typeEnum = GameTypeEnum.UNKNOWN;

    /**
     * game name
     */
    private String name;

    /**
     * Game URL
     */
    private String website;

    /**
     * game introduction
     */
    private String introduce;

    /**
     * game type
     */
    private String gameType;

}
