package com.turn.inscription.dao.param.ppos;

import com.turn.inscription.dao.param.BusinessParam;
import com.turn.inscription.enums.BusinessType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class ProposalVersion implements BusinessParam {

	private String optDesc;
	
    @Override
    public BusinessType getBusinessType() {
        return BusinessType.PROPOSAL_VERSION;
    }
}
