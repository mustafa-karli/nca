package com.nauticana.purchase.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.purchase.model.PurchaseDelivery;
import com.nauticana.purchase.model.PurchaseDeliveryLine;
import com.nauticana.purchase.model.PurchaseDeliveryLineId;

@Service
public class PurchaseDeliveryLineService extends AbstractService<PurchaseDeliveryLine, PurchaseDeliveryLineId> {

	@Autowired
	PurchaseDeliveryService parentService;

	@Override
	@Transactional
	public PurchaseDeliveryLine newEntityWithParentId(String parentKey) {
		PurchaseDeliveryLine entity = new PurchaseDeliveryLine();
		entity.setId(new PurchaseDeliveryLineId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setDeliveryId(parentId);
		entity.setPurchaseDelivery(parentService.findById(parentId));
		return entity;
	}

	@Override
	public PurchaseDeliveryLineId strToId(String id) {
		return new PurchaseDeliveryLineId(id);
	}

	@Override
	public PurchaseDeliveryLine newEntityWithId(String strId) {
		PurchaseDeliveryLine entity = new PurchaseDeliveryLine();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(PurchaseDeliveryLine entity) {
		if (entity == null) return null;
		return PurchaseDelivery.ROOTMAPPING + "/show?id=" + entity.getId().getDeliveryId();
	}

	@Override
	public String idAsStr(PurchaseDeliveryLine entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

