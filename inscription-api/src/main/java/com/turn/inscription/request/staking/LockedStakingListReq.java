package com.turn.inscription.request.staking;

import com.turn.inscription.request.PageReq;

/**
 *  Lock the validator list request object
 */
public class LockedStakingListReq extends PageReq{
    private String key;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}