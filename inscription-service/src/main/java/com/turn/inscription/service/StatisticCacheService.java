package com.turn.inscription.service;

import com.alibaba.fastjson.JSON;
import com.turn.inscription.bean.SubChainTx;
import com.turn.inscription.cache.InscriptionTransferRecordCacheDto;
import com.turn.inscription.cache.SubChainTransactionCacheDto;
import com.turn.inscription.cache.TokenTransferRecordCacheDto;
import com.turn.inscription.cache.TransactionCacheDto;
import com.turn.inscription.dao.entity.NetworkStat;
import com.turn.inscription.dao.entity.TxInscriptionBak;
import com.turn.inscription.elasticsearch.dto.Block;
import com.turn.inscription.elasticsearch.dto.ErcTx;
import com.turn.inscription.elasticsearch.dto.Transaction;
import com.turn.inscription.enums.ErcTypeEnum;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Obtain the specific implementation of statistics cache logic
 */
@Service
public class StatisticCacheService extends CacheBase {
	public List<Block> getBlockCache(Integer pageNum, Integer pageSize) {
		/* Pagination to obtain data based on key */
		CachePageInfo<Class<Block>> cpi = this.getCachePageInfo(redisKeyConfig.getBlocks(), pageNum, pageSize);
		List<Block> blockRedisList = new LinkedList<>();
		cpi.data.forEach(str -> {
			/* Get data and convert it into block object */
			Block blockRedis = JSON.parseObject(str, Block.class);
			blockRedisList.add(blockRedis);
		});
		return blockRedisList;
	}

	public NetworkStat getNetworkStatCache() {
		String value = redisTemplate.opsForValue().get(redisKeyConfig.getNetworkStat());
		/* Get the object and convert it into a statistical object */
		NetworkStat networkStat = JSON.parseObject(value, NetworkStat.class);
		if(networkStat == null) {
			networkStat = new NetworkStat();
		}
		return networkStat;
	}

	public TransactionCacheDto getTransactionCache(Integer pageNum, Integer pageSize) {
		/* Paginate to obtain transaction data based on key */
		CachePageInfo<Class<Transaction>> cpi = this.getCachePageInfo(redisKeyConfig.getTransactions(), pageNum, pageSize);
		List<Transaction> transactionRedisList = new LinkedList<>();
		cpi.data.forEach(str -> {
			/* Get data and convert it into an object */
			Transaction transactionRedis = JSON.parseObject(str, Transaction.class);
			transactionRedisList.add(transactionRedis);
		});
		return new TransactionCacheDto(transactionRedisList, cpi.page);
	}

	public SubChainTransactionCacheDto getSubChainTransactionCache(Integer pageNum, Integer pageSize) {
		/* Paginate to obtain transaction data based on key */
		CachePageInfo<Class<Transaction>> cpi = this.getCachePageInfo(redisKeyConfig.getSubChainTransaction(), pageNum, pageSize);
		List<SubChainTx> subChainTxList = new LinkedList<>();
		cpi.data.forEach(str -> {
			/* Get data and convert it into an object */
			SubChainTx subChainTx = JSON.parseObject(str, SubChainTx.class);
			subChainTxList.add(subChainTx);
		});
		return new SubChainTransactionCacheDto(subChainTxList, cpi.page);
	}

	public List<Block> getBlockCacheByStartEnd(Long start, Long end) {
		/* Pagination to obtain data based on key */
		CachePageInfo<Class<Block>> cpi = this.getCachePageInfoByStartEnd(redisKeyConfig.getBlocks(), start, end);
		List<Block> blockRedisList = new LinkedList<>();
		cpi.data.forEach(str -> {
			/* Get data and convert it into block object */
			Block blockRedis = JSON.parseObject(str, Block.class);
			blockRedisList.add(blockRedis);
		});
		return blockRedisList;
	}

	public TokenTransferRecordCacheDto getTokenTransferCache(Integer pageNum, Integer pageSize, ErcTypeEnum typeEnum) {
		String key = "";
		if (typeEnum == ErcTypeEnum.ERC20) {
			key = redisKeyConfig.getErc20Tx();
		} else if (typeEnum == ErcTypeEnum.ERC721) {
			key = redisKeyConfig.getErc721Tx();
		} else if (typeEnum == ErcTypeEnum.ERC1155) {
			key = redisKeyConfig.getErc1155Tx();
		}
		/* Paginate to obtain transaction data based on key */
		CachePageInfo<Class<ErcTx>> cpi = this.getCachePageInfo(key, pageNum, pageSize);
		List<ErcTx> oldErcTxList = new LinkedList<>();
		cpi.data.forEach(str -> {
			/* Get data and convert it into an object */
			ErcTx tokenTransferRedis = JSON.parseObject(str, ErcTx.class);
			oldErcTxList.add(tokenTransferRedis);
		});
		return new TokenTransferRecordCacheDto(oldErcTxList, cpi.page);
	}

	public InscriptionTransferRecordCacheDto getInscriptionTransferCache(Integer pageNum, Integer pageSize) {

		/* Paginate to obtain transaction data based on key */
		CachePageInfo<Class<TxInscriptionBak>> cpi = this.getCachePageInfo(redisKeyConfig.getInscriptionTx(), pageNum, pageSize);
		List<TxInscriptionBak> oldErcTxList = new LinkedList<>();
		cpi.data.forEach(str -> {
			/* Get data and convert it into an object */
			TxInscriptionBak txInscriptionBak = JSON.parseObject(str, TxInscriptionBak.class);
			oldErcTxList.add(txInscriptionBak);
		});
		return new InscriptionTransferRecordCacheDto(oldErcTxList, cpi.page);
	}
}
