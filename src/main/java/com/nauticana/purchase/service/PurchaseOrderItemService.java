package com.nauticana.purchase.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.purchase.model.PurchaseOrder;
import com.nauticana.purchase.model.PurchaseOrderItem;
import com.nauticana.purchase.model.PurchaseOrderItemId;

@Service
public class PurchaseOrderItemService extends AbstractService<PurchaseOrderItem, PurchaseOrderItemId> {

	@Autowired
	PurchaseOrderService parentService;

	@Override
	@Transactional
	public PurchaseOrderItem newEntityWithParentId(String parentKey) {
		PurchaseOrderItem entity = new PurchaseOrderItem();
		entity.setId(new PurchaseOrderItemId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setPurchaseOrderId(parentId);
		entity.setPurchaseOrder(parentService.findById(parentId));
		return entity;
	}

	@Override
	public PurchaseOrderItemId strToId(String id) {
		return new PurchaseOrderItemId(id);
	}

	@Override
	public PurchaseOrderItem newEntityWithId(String strId) {
		PurchaseOrderItem entity = new PurchaseOrderItem();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(PurchaseOrderItem entity) {
		if (entity == null) return null;
		return PurchaseOrder.ROOTMAPPING + "/show?id=" + entity.getId().getPurchaseOrderId();
	}

	@Override
	public String idAsStr(PurchaseOrderItem entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

