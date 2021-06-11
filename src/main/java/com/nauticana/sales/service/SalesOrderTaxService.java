package com.nauticana.sales.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.sales.model.SalesOrderItemId;
import com.nauticana.sales.model.SalesOrderTax;
import com.nauticana.sales.model.SalesOrderTaxId;

@Service
public class SalesOrderTaxService extends AbstractService<SalesOrderTax, SalesOrderTaxId> {

	@Autowired
	SalesOrderItemService parentService;

	@Override
	@Transactional
	public SalesOrderTax newEntityWithParentId(String parentKey) {
		SalesOrderTax entity = new SalesOrderTax();
		entity.setId(new SalesOrderTaxId());
		SalesOrderItemId parentId = parentService.strToId(parentKey);
		entity.getId().setSalesOrderId(parentId.getSalesOrderId());
		entity.getId().setLine(parentId.getLine());
		entity.setSalesOrderItem(parentService.findById(parentId));
		return entity;
	}

	@Override
	public SalesOrderTaxId strToId(String id) {
		return new SalesOrderTaxId(id);
	}

	@Override
	public SalesOrderTax newEntityWithId(String strId) {
		SalesOrderTax entity = new SalesOrderTax();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(SalesOrderTax entity) {
		if (entity == null) return null;
		if (entity.getSalesOrderItem() == null) return null;
		return "salesOrderItem/show?id=" + entity.getSalesOrderItem().getId().toString();
	}

	@Override
	public String idAsStr(SalesOrderTax entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

