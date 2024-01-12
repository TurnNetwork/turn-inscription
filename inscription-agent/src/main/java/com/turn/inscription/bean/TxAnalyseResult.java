package com.turn.inscription.bean;

import com.turn.inscription.elasticsearch.dto.DelegationReward;
import com.turn.inscription.elasticsearch.dto.NodeOpt;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class TxAnalyseResult {
    private List<NodeOpt> nodeOptList;
    private List<DelegationReward> delegationRewardList;
    private int proposalQty;
}
