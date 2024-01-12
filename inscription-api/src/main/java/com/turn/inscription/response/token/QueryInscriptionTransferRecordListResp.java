package com.turn.inscription.response.token;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.turn.inscription.config.json.CustomLatSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * inscription transaction data
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class QueryInscriptionTransferRecordListResp {

    private Long seq;
    private String txHash;
    private Long blockNumber;
    private String txFrom;
    private String contract;
    private String transferTo;
    private BigDecimal transferValue;
    private String tick;
    private Integer result;
    private Date blockTimestamp;
    private BigDecimal value;

    private String inscriptionId;
    private Long systemTimestamp;

    private Long num;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getTxHash() {
        return this.txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash == null ? null : txHash.trim();
    }

    public Long getBlockNumber() {
        return this.blockNumber;
    }

    public void setBlockNumber(Long blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getTxFrom() {
        return this.txFrom;
    }

    public void setTxFrom(String txFrom) {
        this.txFrom = txFrom == null ? null : txFrom.trim();
    }

    public String getContract() {
        return this.contract;
    }

    public void setContract(String contract) {
        this.contract = contract == null ? null : contract.trim();
    }

    public String getTransferTo() {
        return this.transferTo;
    }

    public void setTransferTo(String transferTo) {
        this.transferTo = transferTo == null ? null : transferTo.trim();
    }

    public BigDecimal getTransferValue() {
        return this.transferValue;
    }

    public void setTransferValue(BigDecimal transferValue) {
        this.transferValue = transferValue;
    }

    public String getTick() {
        return this.tick;
    }

    public void setTick(String tick) {
        this.tick = tick == null ? null : tick.trim();
    }

    public Integer getResult() {
        return this.result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getBlockTimestamp() {
        return this.blockTimestamp;
    }

    public void setBlockTimestamp(Date blockTimestamp) {
        this.blockTimestamp = blockTimestamp;
    }

    @JsonSerialize(using = CustomLatSerializer.class)
    public BigDecimal getValue() {
        return this.value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getSystemTimestamp() {
        return this.systemTimestamp;
    }

    public void setSystemTimestamp(Long systemTimestamp) {
        this.systemTimestamp = systemTimestamp;
    }

    public static enum TransferType {

        INPUT("INPUT"),
        OUT("OUT"),
        NONE("NONE"),
        ;

        String type;

        TransferType(String type) {
            this.type = type;
        }

        public String val() {
            return this.type;
        }
    }

}
