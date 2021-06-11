package com.nauticana.sales.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.sales.model.SalesOrder;
import com.nauticana.sales.model.SalesOrderItem;
import com.nauticana.sales.model.SalesOrderItemId;

@Service
public class SalesOrderItemService extends AbstractService<SalesOrderItem, SalesOrderItemId> {

	@Autowired
	SalesOrderService parentService;

	@Override
	@Transactional
	public SalesOrderItem newEntityWithParentId(String parentKey) {
		SalesOrderItem entity = new SalesOrderItem();
		entity.setId(new SalesOrderItemId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setSalesOrderId(parentId);
		entity.setSalesOrder(parentService.findById(parentId));
		return entity;
	}

	@Override
	public SalesOrderItemId strToId(String id) {
		return new SalesOrderItemId(id);
	}

	@Override
	public SalesOrderItem newEntityWithId(String strId) {
		SalesOrderItem entity = new SalesOrderItem();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(SalesOrderItem entity) {
		if (entity == null) return null;
		return SalesOrder.ROOTMAPPING + "/show?id=" + entity.getId().getSalesOrderId();
	}

	@Override
	public String idAsStr(SalesOrderItem entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

