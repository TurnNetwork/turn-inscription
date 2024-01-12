package com.turn.inscription.decoder.ppos;

import com.bubble.protocol.core.methods.response.Log;
import com.bubble.rlp.solidity.RlpDecoder;
import com.bubble.rlp.solidity.RlpList;
import com.bubble.rlp.solidity.RlpString;
import com.turn.inscription.param.ReleaseBubbleParam;
import com.turn.inscription.param.TxParam;

import java.math.BigInteger;
import java.util.List;

public class ReleaseBubbleDecoder extends AbstractPPOSDecoder {

    private ReleaseBubbleDecoder() {
    }

    public static TxParam decode(RlpList rootList, List<Log> logs) {
        RlpString rlpString = (RlpString) rootList.getValues().get(1);
        BigInteger bubbleId = ((RlpString) RlpDecoder.decode((rlpString.getBytes()))
                .getValues()
                .get(0)).asPositiveBigInteger();
        ReleaseBubbleParam releaseBubbleParam= new ReleaseBubbleParam();
        releaseBubbleParam.setBubbleId(bubbleId);
        return releaseBubbleParam;
    }

}
