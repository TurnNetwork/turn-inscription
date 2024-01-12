package com.turn.inscription.request.inscription;

import com.turn.inscription.request.PageReq;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *  Inscription holder list request object
 */
public class InscriptionHolderReq extends PageReq{
	@NotBlank(message = "{address not null}")
	@Size(min = 42, max = 42)
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (StringUtils.isBlank(address)) return;
		this.address = address;
	}
}