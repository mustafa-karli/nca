package com.nauticana.request.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.service.MaterialTypeService;
import com.nauticana.request.model.ProductTypeByDefine;
import com.nauticana.request.model.ProductTypeByDefineId;

@Service
public class ProductTypeByDefineService extends AbstractService<ProductTypeByDefine, ProductTypeByDefineId> {

	@Autowired
	MaterialTypeService parentService;

	@Override
	@Transactional
	public ProductTypeByDefine newEntityWithParentId(String parentKey) {
		ProductTypeByDefine entity = new ProductTypeByDefine();
		entity.setId(new ProductTypeByDefineId());
		entity.getId().setMaterialTypeId(parentKey);
		entity.setMaterialType(parentService.findById(parentKey));
		return entity;
	}

	@Override
	public ProductTypeByDefineId strToId(String id) {
		return new ProductTypeByDefineId(id);
	}

	@Override
	public ProductTypeByDefine newEntityWithId(String strId) {
		ProductTypeByDefine entity = new ProductTypeByDefine();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<ProductTypeByDefine> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(ProductTypeByDefine x : list) {
			i++;
			items[i][0] = x.getId().getMaterialTypeId();
			items[i][1] = x.getMaterialType().getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(ProductTypeByDefine entity) {
		if (entity == null) return null;
		return "materialType/show?id=" + entity.getId().getMaterialTypeId();
	}

	@Override
	public String idAsStr(ProductTypeByDefine entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}