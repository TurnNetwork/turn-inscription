package com.turn.inscription.bean;

import com.turn.inscription.dao.param.ppos.DelegateExit;
import com.turn.inscription.elasticsearch.dto.DelegationReward;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class DelegateExitResult {
    private DelegateExit delegateExit;
    private DelegationReward delegationReward;
}
