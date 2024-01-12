package com.turn.inscription.request.inscription;


import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Inscription details request object
 */
public class InscriptionDetailsReq {

    @NotBlank(message = "{inscriptionId not null}")
    @Size(min = 66, max = 66)
    private String inscriptionId;

    public String getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(String inscriptionId) {
        if (StringUtils.isBlank(inscriptionId)) return;
        this.inscriptionId = inscriptionId;
    }

}