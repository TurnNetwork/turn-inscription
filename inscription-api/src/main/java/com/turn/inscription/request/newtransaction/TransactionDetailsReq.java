package com.turn.inscription.request.newtransaction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import com.turn.inscription.utils.HexUtil;

/**
 * Transaction details request object
 */
public class TransactionDetailsReq{
    @NotBlank(message = "{txHash not null}")
    @Size(min = 60,max = 66)
    private String txHash;

	public String getTxHash() {
		return txHash;
	}

	public void setTxHash(String txHash) {
		if(StringUtils.isBlank(txHash)) return;
		this.txHash = HexUtil.prefix(txHash.toLowerCase());
	}
    
}