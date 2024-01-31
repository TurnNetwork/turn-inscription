package com.turn.inscription.dao.custommapper;

import com.github.pagehelper.Page;
import com.turn.inscription.bean.InscriptionHolderCount;
import com.turn.inscription.dao.entity.Inscription;
import com.turn.inscription.dao.entity.InscriptionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomInscriptionMapper {

    Page<Inscription> selectListByExample(InscriptionExample inscriptionExample);

    Inscription selectByInscriptionId(String inscriptionId);

    void updateMinted(@Param("contractAddress") String contractAddress, @Param("limitPerMint")Long limitPerMint);

    void updateHolders(@Param("holdCount") Integer holdCount, @Param("contractAddress") String contractAddress);
}