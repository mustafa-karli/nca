package com.nauticana.production.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.production.model.ProductionJob;
import com.nauticana.production.model.ProductionJobResource;
import com.nauticana.production.model.ProductionJobResourceId;

@Service
public class ProductionJobResourceService extends AbstractService<ProductionJobResource, ProductionJobResourceId> {

	@Autowired
	ProductionJobService parentService;

	@Override
	@Transactional
	public ProductionJobResource newEntityWithParentId(String parentKey) {
		ProductionJobResource entity = new ProductionJobResource();
		entity.setId(new ProductionJobResourceId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setProductionJobId(parentId);
		entity.setProductionJob(parentService.findById(parentId));
		return entity;
	}

	@Override
	public ProductionJobResourceId strToId(String id) {
		return new ProductionJobResourceId(id);
	}

	@Override
	public ProductionJobResource newEntityWithId(String strId) {
		ProductionJobResource entity = new ProductionJobResource();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProductionJobResource entity) {
		if (entity == null) return null;
		return ProductionJob.ROOTMAPPING + "/show?id=" + entity.getId().getProductionJobId();
	}

	@Override
	public String idAsStr(ProductionJobResource entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

