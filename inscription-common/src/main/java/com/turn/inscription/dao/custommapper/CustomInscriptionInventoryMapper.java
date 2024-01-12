package com.turn.inscription.dao.custommapper;

import com.github.pagehelper.Page;
import com.turn.inscription.dao.entity.Inscription;
import com.turn.inscription.dao.entity.InscriptionExample;
import com.turn.inscription.dao.entity.InscriptionInventory;
import com.turn.inscription.dao.entity.InscriptionInventoryExample;

public interface CustomInscriptionInventoryMapper {

    Page<InscriptionInventory> selectListByExample(InscriptionInventoryExample inscriptionInventoryExample);
}