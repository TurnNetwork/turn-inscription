package com.turn.inscription.request.address;

import com.turn.inscription.request.PageReq;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *  Query address lock details request object
 */
public class QueryRPPlanDetailRequest extends PageReq{
    @NotBlank(message = "{address not null}")
    @Size(min = 42,max = 42)
    private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address.toLowerCase();
	}
    
}