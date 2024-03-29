package com.turn.inscription.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EditCandidateParam extends TxParam {

    private String nodeId;

    private String beneficiary;

    private String name;

    private String details;

    private String rpcUri;

}
