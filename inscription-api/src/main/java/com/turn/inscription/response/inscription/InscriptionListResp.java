package com.turn.inscription.response.inscription;

import java.math.BigDecimal;
import java.util.Date;

public class InscriptionListResp {

    private String contractAddress;

    private String inscriptionId;

    private String tick;

    private Long totalSupply;

    private Long limitPerMint;

    private Long maxPerWallet;

    private Integer needExtendValue;

    private String deployBy;

    private Date deployTime;

    private Integer holders;

    private Long totalTx;

    private BigDecimal progress;

    private Long minted;

    public Long getMinted() {
        return minted;
    }

    public void setMinted(Long minted) {
        this.minted = minted;
    }

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(String inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    public String getTick() {
        return tick;
    }

    public void setTick(String tick) {
        this.tick = tick;
    }

    public Long getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Long totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Long getLimitPerMint() {
        return limitPerMint;
    }

    public void setLimitPerMint(Long limitPerMint) {
        this.limitPerMint = limitPerMint;
    }

    public Long getMaxPerWallet() {
        return maxPerWallet;
    }

    public void setMaxPerWallet(Long maxPerWallet) {
        this.maxPerWallet = maxPerWallet;
    }

    public Integer getNeedExtendValue() {
        return needExtendValue;
    }

    public void setNeedExtendValue(Integer needExtendValue) {
        this.needExtendValue = needExtendValue;
    }

    public String getDeployBy() {
        return deployBy;
    }

    public void setDeployBy(String deployBy) {
        this.deployBy = deployBy;
    }

    public Date getDeployTime() {
        return deployTime;
    }

    public void setDeployTime(Date deployTime) {
        this.deployTime = deployTime;
    }

    public Integer getHolders() {
        return holders;
    }

    public void setHolders(Integer holders) {
        this.holders = holders;
    }

    public Long getTotalTx() {
        return totalTx;
    }

    public void setTotalTx(Long totalTx) {
        this.totalTx = totalTx;
    }
}
