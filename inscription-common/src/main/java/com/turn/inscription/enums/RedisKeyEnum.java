package com.turn.inscription.enums;

/**
 * redis key
 */
public enum RedisKeyEnum {

    Block("Block"),
    Transaction("Transaction"),
    Statistic("Statistic"),
    Erc20Tx("Erc20Tx"),
    Erc721Tx("Erc721Tx"),
    Erc1155Tx("Erc1155Tx"),
    SubChainTransaction("SubChainTransaction");

    private String key;

    RedisKeyEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
