package com.turn.inscription.bean;

import com.turn.inscription.dao.entity.Game;
import com.turn.inscription.dao.entity.Inscription;
import com.turn.inscription.enums.InscriptionTypeEnum;
import lombok.Data;

import java.util.Date;

@Data
public class InscriptionVO extends Inscription {
    private InscriptionTypeEnum typeEnum;
    private boolean dirty = false;
    public synchronized void setDirty(boolean bol){
        this.dirty=bol;
    }
    public InscriptionVO() {
        Date date = new Date();
        setCreateTime(date);
        setUpdateTime(date);
    }
}
