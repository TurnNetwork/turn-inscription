package com.turn.inscription.param;

import com.turn.inscription.param.claim.Reward;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * tyType=5000
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
public class DelegateRewardClaimParam extends TxParam{
    private List<Reward> rewardList;
}
