package com.nauticana.material.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.MaterialTypeAttribute;
import com.nauticana.material.model.MaterialTypeAttributeId;

@Service
public class MaterialTypeAttributeService extends AbstractService<MaterialTypeAttribute, MaterialTypeAttributeId> {

	@Autowired
	MaterialTypeService parentService;

	@Override
	@Transactional
	public MaterialTypeAttribute newEntityWithParentId(String parentKey) {
		MaterialTypeAttribute entity = new MaterialTypeAttribute();
		entity.setId(new MaterialTypeAttributeId());
		String parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialTypeId(parentId);
		entity.setMaterialType(parentService.findById(parentId));
		return entity;
	}

	@Override
	public MaterialTypeAttributeId strToId(String id) {
		return new MaterialTypeAttributeId(id);
	}

	@Override
	public MaterialTypeAttribute newEntityWithId(String strId) {
		MaterialTypeAttribute entity = new MaterialTypeAttribute();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaterialTypeAttribute entity) {
		if (entity == null) return null;
		return "materialType/show?id=" + entity.getId().getMaterialTypeId();
	}

	@Override
	public String idAsStr(MaterialTypeAttribute entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

