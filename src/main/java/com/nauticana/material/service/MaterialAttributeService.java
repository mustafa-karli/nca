package com.nauticana.material.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.MaterialAttribute;
import com.nauticana.material.model.MaterialAttributeId;

@Service
public class MaterialAttributeService extends AbstractService<MaterialAttribute, MaterialAttributeId> {

	@Autowired
	MaterialService parentService;

	@Override
	@Transactional
	public MaterialAttribute newEntityWithParentId(String parentKey) {
		MaterialAttribute entity = new MaterialAttribute();
		entity.setId(new MaterialAttributeId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId);
		entity.setMaterial(parentService.findById(parentId));
		return entity;
	}

	@Override
	public MaterialAttributeId strToId(String id) {
		return new MaterialAttributeId(id);
	}

	@Override
	public MaterialAttribute newEntityWithId(String strId) {
		MaterialAttribute entity = new MaterialAttribute();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaterialAttribute entity) {
		if (entity == null) return null;
		return "material/show?id=" + entity.getId().getMaterialId();
	}

	@Override
	public String idAsStr(MaterialAttribute entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

