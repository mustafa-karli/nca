package com.nauticana.request.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.request.model.ProductByDefine;
import com.nauticana.request.model.ProductByDefineId;
import com.nauticana.request.model.ProductTypeByDefineId;

@Service
public class ProductByDefineService extends AbstractService<ProductByDefine, ProductByDefineId> {

	@Autowired
	ProductTypeByDefineService parentService;

	@Override
	@Transactional
	public ProductByDefine newEntityWithParentId(String parentKey) {
		ProductByDefine entity = new ProductByDefine();
		ProductTypeByDefineId parentId = parentService.strToId(parentKey);
		entity.setId(new ProductByDefineId());
		entity.getId().setMaterialTypeId(parentId.getMaterialTypeId());
		entity.getId().setOrganizationId(parentId.getOrganizationId());
		entity.setProductTypeByDefine(parentService.findById(parentId));
		return entity;
	}

	@Override
	public ProductByDefineId strToId(String id) {
		return new ProductByDefineId(id);
	}

	@Override
	public ProductByDefine newEntityWithId(String strId) {
		ProductByDefine entity = new ProductByDefine();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProductByDefine entity) {
		if (entity == null) return null;
		if (entity.getProductTypeByDefine() == null) return null;
		return "productTypeByDefine/show?id=" + entity.getProductTypeByDefine().getId();
	}

	@Override
	public String idAsStr(ProductByDefine entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}