package com.nauticana.purchase.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.purchase.model.PurchaseOrderItem;
import com.nauticana.purchase.model.PurchaseOrderItemId;
import com.nauticana.purchase.model.PurchaseReason;
import com.nauticana.purchase.model.PurchaseReasonId;

@Service
public class PurchaseReasonService extends AbstractService<PurchaseReason, PurchaseReasonId> {

	@Autowired
	PurchaseOrderItemService parentService;

	@Override
	@Transactional
	public PurchaseReason newEntityWithParentId(String parentKey) {
		PurchaseReason entity = new PurchaseReason();
		entity.setId(new PurchaseReasonId());
		PurchaseOrderItemId parentId = parentService.strToId(parentKey);
		entity.getId().setPurchaseOrderId(parentId.getPurchaseOrderId());
		entity.getId().setLine(parentId.getLine());
		entity.setPurchaseOrderItem(parentService.findById(parentId));
		return entity;
	}

	@Override
	public PurchaseReasonId strToId(String id) {
		return new PurchaseReasonId(id);
	}

	@Override
	public PurchaseReason newEntityWithId(String strId) {
		PurchaseReason entity = new PurchaseReason();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(PurchaseReason entity) {
		if (entity == null) return null;
		if (entity.getPurchaseOrderItem() == null) return null;
		return PurchaseOrderItem.ROOTMAPPING + "/show?id=" + entity.getPurchaseOrderItem().getId().toString();
	}

	@Override
	public String idAsStr(PurchaseReason entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

