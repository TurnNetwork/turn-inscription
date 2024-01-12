package com.turn.inscription.cache;

import com.turn.inscription.elasticsearch.dto.ErcTx;
import com.turn.inscription.response.RespPage;

import java.util.ArrayList;
import java.util.List;


public class TokenTransferRecordCacheDto {

	public TokenTransferRecordCacheDto() {
		this.transferRecordList = new ArrayList<>();
	}

	public TokenTransferRecordCacheDto(List<ErcTx> transferRecordList, RespPage page) {
		this.transferRecordList = transferRecordList;
		this.page = page;
	}
	private List<ErcTx> transferRecordList;
	
	private RespPage page;

	public List<ErcTx> getTransferRecordList() {
		return transferRecordList;
	}

	public void setTransferRecordList(List<ErcTx> transferRecordList) {
		this.transferRecordList = transferRecordList;
	}

	public RespPage getPage() {
		return page;
	}

	public void setPage(RespPage page) {
		this.page = page;
	}
	
	
	
}
