package com.nauticana.material.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.MaterialSalePrice;
import com.nauticana.material.model.MaterialSalePriceId;

@Service
public class MaterialSalePriceService extends AbstractService<MaterialSalePrice, MaterialSalePriceId> {

	@Autowired
	MaterialService parentService;

	@Override
	@Transactional
	public MaterialSalePrice newEntityWithParentId(String parentKey) {
		MaterialSalePrice entity = new MaterialSalePrice();
		entity.setId(new MaterialSalePriceId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId);
		entity.setMaterial(parentService.findById(parentId));
		return entity;
	}

	@Override
	public MaterialSalePriceId strToId(String id) {
		return new MaterialSalePriceId(id);
	}

	@Override
	public MaterialSalePrice newEntityWithId(String strId) {
		MaterialSalePrice entity = new MaterialSalePrice();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaterialSalePrice entity) {
		if (entity == null) return null;
		return "material/show?id=" + entity.getId().getMaterialId();
	}

	@Override
	public String idAsStr(MaterialSalePrice entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

