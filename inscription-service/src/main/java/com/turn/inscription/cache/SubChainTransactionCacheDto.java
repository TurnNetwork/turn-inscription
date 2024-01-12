package com.turn.inscription.cache;

import com.turn.inscription.bean.SubChainTx;
import com.turn.inscription.response.RespPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Sub chain Transaction cache dto
 */
public class SubChainTransactionCacheDto {

	public SubChainTransactionCacheDto() {
		this.transactionList = new ArrayList<>();
	}
	/**
	 * Transaction construction initial method
	 * @param page
	 */
	public SubChainTransactionCacheDto(List<SubChainTx> transactionList, RespPage page) {
		this.transactionList = transactionList;
		this.page = page;
	}
	private List<SubChainTx> transactionList;
	
	private RespPage page;

	public List<SubChainTx> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<SubChainTx> transactionList) {
		this.transactionList = transactionList;
	}

	public RespPage getPage() {
		return page;
	}

	public void setPage(RespPage page) {
		this.page = page;
	}
	
	
	
}
