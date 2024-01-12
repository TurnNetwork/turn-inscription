package com.turn.inscription.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum InscriptionTxTypeEnum {
    MINT(1, "Mint"),
    TRANSFER(2, "Transfer");

    private int code;
    private String desc;

    InscriptionTxTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    private static final Map<Integer, InscriptionTxTypeEnum> ENUMS = new HashMap<>();
    static {
        Arrays.asList(InscriptionTxTypeEnum.values()).forEach(en -> ENUMS.put(en.code, en));
    }

    public static InscriptionTxTypeEnum getEnum(Integer code) {
        return ENUMS.get(code);
    }

    public static boolean contains(int code) {
        return ENUMS.containsKey(code);
    }

    public static boolean contains(InscriptionTxTypeEnum en) {
        return ENUMS.containsValue(en);
    }
}
