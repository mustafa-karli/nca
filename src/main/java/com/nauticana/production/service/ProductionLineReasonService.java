package com.nauticana.production.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.production.model.ProductionLineReason;
import com.nauticana.production.model.ProductionLineReasonId;
import com.nauticana.production.model.ProductionOrderItem;
import com.nauticana.production.model.ProductionOrderItemId;

@Service
public class ProductionLineReasonService extends AbstractService<ProductionLineReason, ProductionLineReasonId> {

	@Autowired
	ProductionOrderItemService parentService;

	@Override
	@Transactional
	public ProductionLineReason newEntityWithParentId(String parentKey) {
		ProductionLineReason entity = new ProductionLineReason();
		entity.setId(new ProductionLineReasonId());
		ProductionOrderItemId parentId = parentService.strToId(parentKey);
		entity.getId().setProductionOrderId(parentId.getProductionOrderId());
		entity.getId().setLine(parentId.getLine());
		entity.setProductionOrderItem(parentService.findById(parentId));
		return entity;
	}

	@Override
	public ProductionLineReasonId strToId(String id) {
		return new ProductionLineReasonId(id);
	}

	@Override
	public ProductionLineReason newEntityWithId(String strId) {
		ProductionLineReason entity = new ProductionLineReason();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProductionLineReason entity) {
		if (entity == null) return null;
		return ProductionOrderItem.ROOTMAPPING + "/show?id=" + entity.getProductionOrderItem().getId().toString();
	}

	@Override
	public String idAsStr(ProductionLineReason entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

