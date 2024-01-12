package com.turn.inscription.param;

import com.alibaba.fastjson.JSON;

public abstract class TxParam {
    public String toJSONString(){
        return JSON.toJSONString(this);
    }
}
