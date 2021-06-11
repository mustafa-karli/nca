package com.nauticana.material.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.MaterialSaleTax;
import com.nauticana.material.model.MaterialSaleTaxId;

@Service
public class MaterialSaleTaxService extends AbstractService<MaterialSaleTax, MaterialSaleTaxId> {

	@Autowired
	MaterialService parentService;

	@Override
	@Transactional
	public MaterialSaleTax newEntityWithParentId(String parentKey) {
		MaterialSaleTax entity = new MaterialSaleTax();
		entity.setId(new MaterialSaleTaxId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId);
		entity.setMaterial(parentService.findById(parentId));
		return entity;
	}

	@Override
	public MaterialSaleTaxId strToId(String id) {
		return new MaterialSaleTaxId(id);
	}

	@Override
	public MaterialSaleTax newEntityWithId(String strId) {
		MaterialSaleTax entity = new MaterialSaleTax();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaterialSaleTax entity) {
		if (entity == null) return null;
		return "material/show?id=" + entity.getId().getMaterialId();
	}

	@Override
	public String idAsStr(MaterialSaleTax entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

