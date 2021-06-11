package com.nauticana.production.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.production.model.ProductionOrder;
import com.nauticana.production.model.ProductionOrderItem;
import com.nauticana.production.model.ProductionOrderItemId;

@Service
public class ProductionOrderItemService extends AbstractService<ProductionOrderItem, ProductionOrderItemId> {

	@Autowired
	ProductionOrderService parentService;

	@Override
	@Transactional
	public ProductionOrderItem newEntityWithParentId(String parentKey) {
		ProductionOrderItem entity = new ProductionOrderItem();
		entity.setId(new ProductionOrderItemId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setProductionOrderId(parentId);
		entity.setProductionOrder(parentService.findById(parentId));
		return entity;
	}

	@Override
	public ProductionOrderItemId strToId(String id) {
		return new ProductionOrderItemId(id);
	}

	@Override
	public ProductionOrderItem newEntityWithId(String strId) {
		ProductionOrderItem entity = new ProductionOrderItem();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProductionOrderItem entity) {
		if (entity == null) return null;
		return ProductionOrder.ROOTMAPPING + "/show?id=" + entity.getId().getProductionOrderId();
	}

	@Override
	public String idAsStr(ProductionOrderItem entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

