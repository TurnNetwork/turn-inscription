package com.turn.inscription.request.token;

import com.turn.inscription.request.PageReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Query the contract internal transaction transfer list
 * condition:
 * 1. Query from the contract dimension;
 * 2. Query from address dimension
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryTokenTransferRecordListReq extends PageReq {

    private String contract;

    private String address;

    private String txHash;

    /**
     * token_id
     */
    private String tokenId;

}
