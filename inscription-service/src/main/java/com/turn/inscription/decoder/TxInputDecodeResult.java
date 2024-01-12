package com.turn.inscription.decoder;

import com.turn.inscription.elasticsearch.dto.Transaction;
import com.turn.inscription.param.OthersTxParam;
import com.turn.inscription.param.TxParam;

/**
 * @description: Decoded result
 **/
public class TxInputDecodeResult {
    private TxParam param= new OthersTxParam();
    private Transaction.TypeEnum typeEnum= Transaction.TypeEnum.OTHERS;

    public TxParam getParam() {
        return param;
    }

    public TxInputDecodeResult setParam(TxParam param) {
        this.param = param;
        return this;
    }

    public Transaction.TypeEnum getTypeEnum() {
        return typeEnum;
    }

    public TxInputDecodeResult setTypeEnum(Transaction.TypeEnum typeEnum) {
        this.typeEnum = typeEnum;
        return this;
    }
}
