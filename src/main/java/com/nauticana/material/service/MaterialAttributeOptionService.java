package com.nauticana.material.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.MaterialAttributeOption;
import com.nauticana.material.model.MaterialAttributeOptionId;

@Service
public class MaterialAttributeOptionService extends AbstractService<MaterialAttributeOption, MaterialAttributeOptionId> {

	@Autowired
	MaterialAttributeGroupService parentService;

	@Override
	@Transactional
	public MaterialAttributeOption newEntityWithParentId(String parentKey) {
		MaterialAttributeOption entity = new MaterialAttributeOption();
		entity.setId(new MaterialAttributeOptionId());
		entity.getId().setMagId(parentKey);
		entity.setMaterialAttributeGroup(parentService.findById(parentKey));
		return entity;
	}

	@Override
	public MaterialAttributeOptionId strToId(String id) {
		return new MaterialAttributeOptionId(id);
	}

	@Override
	public MaterialAttributeOption newEntityWithId(String strId) {
		MaterialAttributeOption entity = new MaterialAttributeOption();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaterialAttributeOption entity) {
		if (entity == null) return null;
		return "materialAttributeGroup/show?id=" + entity.getId().getMagId();
	}

	@Override
	public String idAsStr(MaterialAttributeOption entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

