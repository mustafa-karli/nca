package com.nauticana.sales.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.sales.model.SalesAccountBalance;

@Service
public class SalesAccountBalanceService extends AbstractService<SalesAccountBalance, Integer> {

	@Override
	@Transactional
	public SalesAccountBalance newEntityWithParentId(String parentKey) {
		SalesAccountBalance entity = new SalesAccountBalance();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(SalesAccountBalance entity, int client) {
		entity.setId(client);
	};

	@Override
	public int getClient(SalesAccountBalance entity) {
		return entity.getId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public SalesAccountBalance newEntityWithId(String strId) {
		SalesAccountBalance entity = new SalesAccountBalance();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(SalesAccountBalance entity) {
		return null;
	}

	@Override
	public String idAsStr(SalesAccountBalance entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

}