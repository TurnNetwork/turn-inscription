package com.turn.inscription.response.inscription;

import java.math.BigDecimal;
import java.util.Date;

public class InscriptionHolderListResp {

    private Integer rank;
    private String address;
    private BigDecimal balance;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
