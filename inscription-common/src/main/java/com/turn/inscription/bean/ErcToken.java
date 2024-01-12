package com.turn.inscription.bean;

import com.turn.inscription.dao.entity.Token;
import com.turn.inscription.enums.ErcTypeEnum;
import lombok.Data;

import java.util.Date;

@Data
public class ErcToken extends Token {
    private ErcTypeEnum typeEnum;
    private boolean dirty = false;
    public synchronized void setDirty(boolean bol){
        this.dirty=bol;
    }
    public ErcToken() {
        setTokenTxQty(0);
        setHolder(0);
        Date date = new Date();
        setCreateTime(date);
        setUpdateTime(date);
    }
}
