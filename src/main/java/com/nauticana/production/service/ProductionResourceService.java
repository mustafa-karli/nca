package com.nauticana.production.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.personnel.model.Organization;
import com.nauticana.personnel.service.OrganizationService;
import com.nauticana.production.model.ProductionResource;
import com.nauticana.production.model.ProductionResourceId;

@Service
public class ProductionResourceService extends AbstractService<ProductionResource, ProductionResourceId> {

	@Autowired
	OrganizationService parentService;

	@Override
	@Transactional
	public ProductionResource newEntityWithParentId(String parentKey) {
		ProductionResource entity = new ProductionResource();
		entity.setId(new ProductionResourceId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setOrganizationId(parentId);
		entity.setOrganization(parentService.findById(parentId));
		return entity;
	}

	@Override
	public ProductionResourceId strToId(String id) {
		return new ProductionResourceId(id);
	}

	@Override
	public ProductionResource newEntityWithId(String strId) {
		ProductionResource entity = new ProductionResource();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProductionResource entity) {
		if (entity == null) return null;
		return Organization.ROOTMAPPING + "/show?id=" + entity.getId().getOrganizationId();
	}

	@Override
	public String idAsStr(ProductionResource entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

