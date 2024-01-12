package com.turn.inscription.request.inscription;

import com.turn.inscription.request.PageReq;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *  Inscription holders list request object
 */
public class InscriptionHolderListReq extends PageReq{
	@NotBlank(message = "{inscriptionId not null}")
	@Size(min = 66, max = 66)
	private String inscriptionId;

	private Integer sort;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getInscriptionId() {
		return inscriptionId;
	}

	public void setInscriptionId(String inscriptionId) {
		if (StringUtils.isBlank(inscriptionId)) return;
		this.inscriptionId = inscriptionId;
	}
}