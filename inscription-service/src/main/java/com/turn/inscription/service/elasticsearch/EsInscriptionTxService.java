package com.turn.inscription.service.elasticsearch;

import com.turn.inscription.dao.entity.TxInscriptionBak;
import com.turn.inscription.elasticsearch.dto.ErcTx;
import com.turn.inscription.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Inscription Transaction ES Processor
 */
@Service
@Slf4j
public class EsInscriptionTxService implements EsService<TxInscriptionBak> {

    @Resource
    private EsInscriptionTxRepository esInscriptionTxRepository;

    @Retryable(value = BusinessException.class, maxAttempts = Integer.MAX_VALUE)
    public void save(Set<TxInscriptionBak> recordSet) {
        try {
            if (recordSet.isEmpty()) return;
            // key: _doc id
            Map<String, TxInscriptionBak> txMap = new HashMap<>();
            recordSet.forEach(t -> txMap.put(
                    generateUniqueDocId(t.getHash(),
                            t.getFrom(),
                            t.getTo(),
                            t.getSeq()), t));
            esInscriptionTxRepository.bulkAddOrUpdate(txMap);
        } catch (Exception e) {
            log.error("Batch save data of ESTokenTransferRecord exception", e);
            throw new BusinessException(e.getMessage());
        }
    }

    public String generateUniqueDocId(String txHash, String from, String to, long seq) {
        return seq + "_" + txHash.substring(0, txHash.length() / 2) + from.substring(0, from.length() / 2)
                + from.substring(0, to.length() / 2);
    }
}
