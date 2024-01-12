package com.turn.inscription.request.subchain;

import com.turn.inscription.utils.HexUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Sub-chain transaction details request object
 */
public class SubChainTransactionDetailsReq {
    @NotBlank(message = "{txHash not null}")
    @Size(min = 60,max = 66)
    private String txHash;

	@NotNull(message = "{bubbleId not null}")
	private Long bubbleId;

	public Long getBubbleId() {
		return bubbleId;
	}

	public void setBubbleId(Long bubbleId) {
		this.bubbleId = bubbleId;
	}

	public String getTxHash() {
		return txHash;
	}

	public void setTxHash(String txHash) {
		if(StringUtils.isBlank(txHash)) return;
		this.txHash = HexUtil.prefix(txHash.toLowerCase());
	}
    
}