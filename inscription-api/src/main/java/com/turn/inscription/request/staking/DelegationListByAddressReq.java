package com.turn.inscription.request.staking;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.turn.inscription.request.PageReq;

/**
 *  Address query delegate request object
 */
public class DelegationListByAddressReq extends PageReq{
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