package com.turn.inscription.elasticsearch.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.turn.inscription.bean.Receipt;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Data
@Accessors(chain = true)
public class Block {

    @JSONField(serialize = false)
    protected List<Transaction> transactions = new ArrayList<>();

    @JSONField(serialize = false)
    private Integer erc20TxQty = 0;

    @JSONField(serialize = false)
    private Integer erc721TxQty = 0;

    @JSONField(serialize = false)
    private Integer erc1155TxQty = 0;

    private Long num;

    private String hash;

    private String pHash;

    private Date time;

    private Integer size;

    private String gasLimit;

    private String gasUsed;

    private Integer txQty;

    private Integer tranQty;

    private Integer sQty;

    private Integer pQty;

    private Integer dQty;

    private String txGasLimit;

    private String txFee;

    private String nodeName;

    private String nodeId;

    private String reward;

    private String miner;

    private Date creTime;

    private Date updTime;

    private String extra;

    /**
     * Serial number ID, used to calculate the transaction seq increment under the block
     */
    AtomicLong seq;

    @JSONField(serialize = false)
    private List<com.bubble.protocol.core.methods.response.Transaction> originTransactions = new ArrayList<>();

    /**
     * key: transaction hash, value: receipt
     */
    @JSONField(serialize = false)
    private Map<String, Receipt> receiptMap = new HashMap<>();

    /******** Convenient method to convert string values into large floating point numbers ********/
    public BigDecimal decimalGasLimit() {
        return new BigDecimal(this.getGasLimit());
    }

    public BigDecimal decimalGasUsed() {
        return new BigDecimal(this.getGasUsed());
    }

    public BigDecimal decimalTxGasLimit() {
        return new BigDecimal(this.getTxGasLimit());
    }

    public BigDecimal decimalTxFee() {
        return new BigDecimal(this.getTxFee());
    }

    public BigDecimal decimalReward() {
        return new BigDecimal(this.getReward());
    }


}