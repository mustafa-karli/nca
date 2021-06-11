package com.nauticana.sales.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.sales.model.SalesOrderItemAttr;
import com.nauticana.sales.model.SalesOrderItemAttrId;
import com.nauticana.sales.model.SalesOrderItemId;

@Service
public class SalesOrderItemAttrService extends AbstractService<SalesOrderItemAttr, SalesOrderItemAttrId> {

	@Autowired
	SalesOrderItemService parentService;

	@Override
	@Transactional
	public SalesOrderItemAttr newEntityWithParentId(String parentKey) {
		SalesOrderItemAttr entity = new SalesOrderItemAttr();
		entity.setId(new SalesOrderItemAttrId());
		SalesOrderItemId parentId = parentService.strToId(parentKey);
		entity.getId().setSalesOrderId(parentId.getSalesOrderId());
		entity.getId().setLine(parentId.getLine());
		entity.setSalesOrderItem(parentService.findById(parentId));
		return entity;
	}

	@Override
	public SalesOrderItemAttrId strToId(String id) {
		return new SalesOrderItemAttrId(id);
	}

	@Override
	public SalesOrderItemAttr newEntityWithId(String strId) {
		SalesOrderItemAttr entity = new SalesOrderItemAttr();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(SalesOrderItemAttr entity) {
		if (entity == null) return null;
		if (entity.getSalesOrderItem() == null) return null;
		return "salesOrderItem/show?id=" + entity.getSalesOrderItem().getId().toString();
	}

	@Override
	public String idAsStr(SalesOrderItemAttr entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

