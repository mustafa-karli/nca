package com.nauticana.purchase.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.purchase.model.PurchaseOrder;

@Service
public class PurchaseOrderService extends AbstractService<PurchaseOrder, Integer> {

	@Override
	@Transactional
	public PurchaseOrder newEntityWithParentId(String parentKey) {
		PurchaseOrder entity = new PurchaseOrder();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(PurchaseOrder entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(PurchaseOrder entity) {
		return entity.getOwnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public PurchaseOrder newEntityWithId(String strId) {
		PurchaseOrder entity = new PurchaseOrder();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(PurchaseOrder entity) {
		return null;
	}

	@Override
	public String idAsStr(PurchaseOrder entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

}

