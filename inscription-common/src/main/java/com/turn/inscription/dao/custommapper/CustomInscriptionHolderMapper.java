package com.turn.inscription.dao.custommapper;

import com.github.pagehelper.Page;
import com.turn.inscription.dao.entity.InscriptionHolder;
import com.turn.inscription.dao.entity.InscriptionHolderExample;
import com.turn.inscription.dao.entity.InscriptionHolderKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomInscriptionHolderMapper {

    int batchInsertOrUpdateSelective(@Param("list") List<InscriptionHolder> list, @Param("selective") InscriptionHolder.Column... selective);

    InscriptionHolder selectByKey(InscriptionHolderKey key);

    Page<InscriptionHolder> selectListByExample(InscriptionHolderExample inscriptionHolderExample);
}
