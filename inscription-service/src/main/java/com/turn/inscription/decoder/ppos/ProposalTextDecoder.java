package com.turn.inscription.decoder.ppos;

import com.bubble.rlp.solidity.RlpList;
import com.bubble.rlp.solidity.RlpString;
import com.bubble.utils.Numeric;
import com.turn.inscription.param.ProposalTextParam;
import com.turn.inscription.param.TxParam;

/**
 * @description: Create a validator transaction input parameter decoder
 **/
public class ProposalTextDecoder extends AbstractPPOSDecoder {
    private ProposalTextDecoder(){}
    public static TxParam decode(RlpList rootList) {
        //Submit text proposal
        //The verifier who submitted the proposal
        String nodeId = stringResolver((RlpString) rootList.getValues().get(1));
        //pIDID
        String pIdID = stringResolver((RlpString) rootList.getValues().get(2));
        pIdID =  new String(Numeric.hexStringToByteArray(pIdID));

        return ProposalTextParam.builder()
                .verifier(nodeId)
                .pIDID(pIdID)
                .build();
    }
}
