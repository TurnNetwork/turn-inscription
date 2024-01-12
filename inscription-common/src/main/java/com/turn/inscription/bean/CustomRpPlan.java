package com.turn.inscription.bean;

import com.turn.inscription.dao.entity.RpPlan;

import java.util.Date;

/**
 * @Description: Lock-up plan entity extension class
 */
public class CustomRpPlan extends RpPlan {
    public CustomRpPlan(){
        super();
        Date date = new Date();
        this.setCreateTime(date);
        this.setUpdateTime(date);
    }
}
