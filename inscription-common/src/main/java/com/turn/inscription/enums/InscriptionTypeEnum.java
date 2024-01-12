package com.turn.inscription.enums;

/**
 * @Description:
 */
public enum InscriptionTypeEnum {
    UNKNOWN("unknown"),
    INSCRIPTION("inscription");

    private String desc;

    InscriptionTypeEnum(String desc) {
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
     * @date 2021/1/19
     */
    public static InscriptionTypeEnum getInscriptionTypeEnum(String name) {
        for (InscriptionTypeEnum e : InscriptionTypeEnum.values()) {
            if (e.getDesc().equals(name)) {
                return e;
            }
        }
        return InscriptionTypeEnum.UNKNOWN;
    }

}