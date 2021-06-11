package com.nauticana.sales.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.sales.model.SalesOrder;

@Service
public class SalesOrderService extends AbstractService<SalesOrder, Integer> {

	@Override
	@Transactional
	public SalesOrder newEntityWithParentId(String parentKey) {
		SalesOrder entity = new SalesOrder();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(SalesOrder entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(SalesOrder entity) {
		return entity.getOwnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public SalesOrder newEntityWithId(String strId) {
		SalesOrder entity = new SalesOrder();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(SalesOrder entity) {
		return null;
	}

	@Override
	public String idAsStr(SalesOrder entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

}

