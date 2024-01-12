package com.turn.inscription.request.inscription;

import com.turn.inscription.request.PageReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Query the contract inscription transaction transfer list
 * condition:
 * 1. Query from the contract dimension;
 * 2. Query from address dimension
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryInscriptionTransferRecordListReq extends PageReq {

    private String address;

    private String txHash;

    private String inscriptionId;

    private Integer type;

}
