package com.turn.inscription.request.inscription;


import com.turn.inscription.request.PageReq;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Inscription Inventory request object
 */
public class InscriptionInventoryListReq extends PageReq {

    @NotBlank(message = "{address not null}")
    @Size(min = 42, max = 42)
    private String address;

    private String inscriptionId;

    private String key;

    public String getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(String inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (StringUtils.isBlank(address)) return;
        this.address = address;
    }

}