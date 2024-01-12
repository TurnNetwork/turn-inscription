package com.turn.inscription.request.token;

import com.turn.inscription.request.PageReq;
import lombok.Data;


/**
 * Query token id list request parameters
 */
@Data
public class QueryTokenIdListReq extends PageReq {

    private String contract;//

    private String address;//

    private String tokenId;//
}
