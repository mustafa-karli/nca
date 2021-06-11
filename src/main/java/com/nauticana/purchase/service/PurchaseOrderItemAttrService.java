package com.nauticana.purchase.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.purchase.model.PurchaseOrderItem;
import com.nauticana.purchase.model.PurchaseOrderItemAttr;
import com.nauticana.purchase.model.PurchaseOrderItemAttrId;
import com.nauticana.purchase.model.PurchaseOrderItemId;

@Service
public class PurchaseOrderItemAttrService extends AbstractService<PurchaseOrderItemAttr, PurchaseOrderItemAttrId> {

	@Autowired
	PurchaseOrderItemService parentService;

	@Override
	@Transactional
	public PurchaseOrderItemAttr newEntityWithParentId(String parentKey) {
		PurchaseOrderItemAttr entity = new PurchaseOrderItemAttr();
		entity.setId(new PurchaseOrderItemAttrId());
		PurchaseOrderItemId parentId = parentService.strToId(parentKey);
		entity.getId().setPurchaseOrderId(parentId.getPurchaseOrderId());
		entity.getId().setLine(parentId.getLine());
		entity.setPurchaseOrderItem(parentService.findById(parentId));
		return entity;
	}

	@Override
	public PurchaseOrderItemAttrId strToId(String id) {
		return new PurchaseOrderItemAttrId(id);
	}

	@Override
	public PurchaseOrderItemAttr newEntityWithId(String strId) {
		PurchaseOrderItemAttr entity = new PurchaseOrderItemAttr();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(PurchaseOrderItemAttr entity) {
		if (entity == null) return null;
		if (entity.getPurchaseOrderItem() == null) return null;
		return PurchaseOrderItem.ROOTMAPPING + "/show?id=" + entity.getPurchaseOrderItem().getId().toString();
	}

	@Override
	public String idAsStr(PurchaseOrderItemAttr entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

