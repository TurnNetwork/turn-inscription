package com.turn.inscription.request.inscription;

import com.turn.inscription.request.PageReq;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 *  Inscription list request object
 */
public class InscriptionListReq extends PageReq{
    private String tick;
    @NotBlank(message = "{queryStatus not null}")
    @Pattern(regexp = "all|In-progress|Completed", message = "{queryStatus.illegal}")
    private String queryStatus;

	private String deployBy;

	public String getDeployBy() {
		return deployBy;
	}

	public void setDeployBy(String deployBy) {
		this.deployBy = deployBy;
	}

	public String getTick() {
		return tick;
	}
	public void setTick(String key) {
		this.tick = key;
	}
	public String getQueryStatus() {
		return queryStatus;
	}
	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}
    
}