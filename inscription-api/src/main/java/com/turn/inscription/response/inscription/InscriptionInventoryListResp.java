package com.turn.inscription.response.inscription;

import java.math.BigDecimal;
import java.util.Date;

public class InscriptionInventoryListResp {

    private String inscriptionId;

    private String owner;

    private String tick;

    private String ext;

    private String num;

    private Date createTime;

    private Date updateTime;

    private Integer inscriptionTxQty;

    private Integer ownerTxQty;

    private Integer retryNum;

    private String description;

    private Long balance;

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(String inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTick() {
        return tick;
    }

    public void setTick(String tick) {
        this.tick = tick;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getInscriptionTxQty() {
        return inscriptionTxQty;
    }

    public void setInscriptionTxQty(Integer inscriptionTxQty) {
        this.inscriptionTxQty = inscriptionTxQty;
    }

    public Integer getOwnerTxQty() {
        return ownerTxQty;
    }

    public void setOwnerTxQty(Integer ownerTxQty) {
        this.ownerTxQty = ownerTxQty;
    }

    public Integer getRetryNum() {
        return retryNum;
    }

    public void setRetryNum(Integer retryNum) {
        this.retryNum = retryNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
