package com.turn.inscription.dao.param.ppos;

import com.turn.inscription.dao.param.BusinessParam;
import com.turn.inscription.enums.BusinessType;
import com.turn.inscription.param.claim.Reward;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * // TODO: Define the warehousing parameters of the reward collection business
 * @Description: Claim Reward
 */
@Data
@Builder
@Accessors(chain = true)
public class DelegateRewardClaim implements BusinessParam {
    //Reward information list
    private List<Reward> rewardList;
    //recipient address
    private String address;

    @Override
    public BusinessType getBusinessType() {
        return BusinessType.CLAIM_REWARD;
    }
}
