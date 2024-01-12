package com.turn.inscription.task.bean;

import lombok.Data;

import java.util.Date;

@Data
public class TokenHolderNum {

    private String tokenAddress;

    private Integer holderNum;

    private Date updateTime;

}
