package com.turn.inscription.enums;


/**
 * @Description:
 */
public enum InscriptionEventTypeEnum {
    MINT_EVENT("getMintEvents"),
    BATCH_TRANSFER_EVENT("getBatchTransferEvents"),
    DEPLOY_EVENT("getDeployEvents");

    private String desc;

    InscriptionEventTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * Get enumeration by name
     *
     * @param name
     * @return com.turn.browser.v0151.enums.ErcTypeEnum
     */
    public static InscriptionEventTypeEnum getErcTypeEnum(String name) {
        for (InscriptionEventTypeEnum e : InscriptionEventTypeEnum.values()) {
            if (e.getDesc().equals(name)) {
                return e;
            }
        }
        return null;
    }

}