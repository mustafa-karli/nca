package com.nauticana.production.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.production.model.ProductionJob;

@Service
public class ProductionJobService extends AbstractService<ProductionJob, Integer> {

	@Override
	@Transactional
	public ProductionJob newEntityWithParentId(String parentKey) {
		ProductionJob entity = new ProductionJob();
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public ProductionJob newEntityWithId(String strId) {
		ProductionJob entity = new ProductionJob();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProductionJob entity) {
		return null;
	}

	@Override
	public String idAsStr(ProductionJob entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

}

