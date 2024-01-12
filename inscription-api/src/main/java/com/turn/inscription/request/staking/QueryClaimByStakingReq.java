package com.turn.inscription.request.staking;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.turn.inscription.request.PageReq;

/**
 * According to the pledged request object
 */
public class QueryClaimByStakingReq extends PageReq {

	@NotBlank(message = "{nodeId not null}")
	@Size(min = 130,max = 130)
	private String nodeId;

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
}
