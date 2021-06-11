package com.nauticana.purchase.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.purchase.model.PurchaseDelivery;

@Service
public class PurchaseDeliveryService extends AbstractService<PurchaseDelivery, Integer> {

	@Override
	@Transactional
	public PurchaseDelivery newEntityWithParentId(String parentKey) {
		PurchaseDelivery entity = new PurchaseDelivery();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(PurchaseDelivery entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(PurchaseDelivery entity) {
		return entity.getOwnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public PurchaseDelivery newEntityWithId(String strId) {
		PurchaseDelivery entity = new PurchaseDelivery();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(PurchaseDelivery entity) {
		return null;
	}

	@Override
	public String idAsStr(PurchaseDelivery entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

}

