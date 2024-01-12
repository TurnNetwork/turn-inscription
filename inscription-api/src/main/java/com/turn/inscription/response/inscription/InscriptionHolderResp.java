package com.turn.inscription.response.inscription;

import java.math.BigDecimal;

public class InscriptionHolderResp {

    private Integer mintTimes;
    private String inscriptionId;
    private BigDecimal balance;

    public Integer getMintTimes() {
        return mintTimes;
    }

    public void setMintTimes(Integer mintTimes) {
        this.mintTimes = mintTimes;
    }

    public String getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(String inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
