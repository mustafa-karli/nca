package com.nauticana.sales.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.sales.model.SalesDeliveryAddress;
import com.nauticana.sales.model.SalesOrder;

@Service
public class SalesDeliveryAddressService extends AbstractService<SalesDeliveryAddress, Integer> {

	@Autowired
	SalesOrderService parentService;

	@Override
	@Transactional
	public SalesDeliveryAddress newEntityWithParentId(String parentKey) {
		SalesDeliveryAddress entity = new SalesDeliveryAddress();
		int parentId = parentService.strToId(parentKey);
		entity.setId(parentId);
		entity.setSalesOrder(parentService.findById(parentId));
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public SalesDeliveryAddress newEntityWithId(String strId) {
		SalesDeliveryAddress entity = new SalesDeliveryAddress();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(SalesDeliveryAddress entity) {
		if (entity == null) return null;
		if (entity.getSalesOrder() == null) return null;
		return SalesOrder.ROOTMAPPING + "/show?id=" + entity.getSalesOrder().getId();
	}

	@Override
	public String idAsStr(SalesDeliveryAddress entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

}

