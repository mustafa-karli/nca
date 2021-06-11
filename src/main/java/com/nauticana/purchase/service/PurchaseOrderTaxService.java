package com.nauticana.purchase.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.purchase.model.PurchaseOrderItem;
import com.nauticana.purchase.model.PurchaseOrderItemId;
import com.nauticana.purchase.model.PurchaseOrderTax;
import com.nauticana.purchase.model.PurchaseOrderTaxId;

@Service
public class PurchaseOrderTaxService extends AbstractService<PurchaseOrderTax, PurchaseOrderTaxId> {

	@Autowired
	PurchaseOrderItemService parentService;

	@Override
	@Transactional
	public PurchaseOrderTax newEntityWithParentId(String parentKey) {
		PurchaseOrderTax entity = new PurchaseOrderTax();
		entity.setId(new PurchaseOrderTaxId());
		PurchaseOrderItemId parentId = parentService.strToId(parentKey);
		entity.getId().setPurchaseOrderId(parentId.getPurchaseOrderId());
		entity.getId().setLine(parentId.getLine());
		entity.setPurchaseOrderItem(parentService.findById(parentId));
		return entity;
	}

	@Override
	public PurchaseOrderTaxId strToId(String id) {
		return new PurchaseOrderTaxId(id);
	}

	@Override
	public PurchaseOrderTax newEntityWithId(String strId) {
		PurchaseOrderTax entity = new PurchaseOrderTax();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(PurchaseOrderTax entity) {
		if (entity == null) return null;
		if (entity.getPurchaseOrderItem() == null) return null;
		return PurchaseOrderItem.ROOTMAPPING + "/show?id=" + entity.getPurchaseOrderItem().getId().toString();
	}

	@Override
	public String idAsStr(PurchaseOrderTax entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

