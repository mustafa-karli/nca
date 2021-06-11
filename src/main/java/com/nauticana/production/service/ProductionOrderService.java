package com.nauticana.production.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.production.model.ProductionOrder;

@Service
public class ProductionOrderService extends AbstractService<ProductionOrder, Integer> {

	@Override
	@Transactional
	public ProductionOrder newEntityWithParentId(String parentKey) {
		ProductionOrder entity = new ProductionOrder();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(ProductionOrder entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(ProductionOrder entity) {
		return entity.getOwnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public ProductionOrder newEntityWithId(String strId) {
		ProductionOrder entity = new ProductionOrder();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProductionOrder entity) {
		return null;
	}

	@Override
	public String idAsStr(ProductionOrder entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

}

