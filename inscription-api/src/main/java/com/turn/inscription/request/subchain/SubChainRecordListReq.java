package com.turn.inscription.request.subchain;

import com.turn.inscription.request.PageReq;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Sub-chain transaction list request object
 */
public class SubChainRecordListReq extends PageReq{
	@NotBlank(message = "{address not null}")
	@Size(min = 42,max = 42)
    private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}