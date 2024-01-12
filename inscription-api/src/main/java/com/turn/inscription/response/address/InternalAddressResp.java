package com.turn.inscription.response.address;

import com.turn.inscription.dao.entity.InternalAddress;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class InternalAddressResp {

    /**
     * Total balance of foundation account
     */
    private BigDecimal totalBalance;

    /**
     * Total locked balance of foundation account
     */
    private BigDecimal totalRestrictingBalance;

    /**
     * Foundation account
     */
    List<InternalAddress> internalAddressBaseResp;

}