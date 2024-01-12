package com.turn.inscription.cache;

import com.turn.inscription.dao.entity.TxInscriptionBak;
import com.turn.inscription.elasticsearch.dto.ErcTx;
import com.turn.inscription.response.RespPage;

import java.util.ArrayList;
import java.util.List;


public class InscriptionTransferRecordCacheDto {

	public InscriptionTransferRecordCacheDto() {
		this.transferRecordList = new ArrayList<>();
	}

	public InscriptionTransferRecordCacheDto(List<TxInscriptionBak> transferRecordList, RespPage page) {
		this.transferRecordList = transferRecordList;
		this.page = page;
	}
	private List<TxInscriptionBak> transferRecordList;
	
	private RespPage page;

	public List<TxInscriptionBak> getTransferRecordList() {
		return transferRecordList;
	}

	public void setTransferRecordList(List<TxInscriptionBak> transferRecordList) {
		this.transferRecordList = transferRecordList;
	}

	public RespPage getPage() {
		return page;
	}

	public void setPage(RespPage page) {
		this.page = page;
	}
	
	
	
}
