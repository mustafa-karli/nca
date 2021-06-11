package com.nauticana.material.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.MaterialMovement;

@Service
public class MaterialMovementService extends AbstractService<MaterialMovement, Integer> {

	@Autowired
	MaterialService parentService;

	@Override
	@Transactional
	public MaterialMovement newEntityWithParentId(String parentKey) {
		MaterialMovement entity = new MaterialMovement();
		int parentId = parentService.strToId(parentKey);
		entity.setMaterial(parentService.findById(parentId));
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public MaterialMovement newEntityWithId(String strId) {
		MaterialMovement entity = new MaterialMovement();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaterialMovement entity) {
		if (entity == null) return null;
		if (entity.getMaterial() == null) return null;
		return "material/show?id=" + entity.getMaterial().getId();
	}

	@Override
	public String idAsStr(MaterialMovement entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}
}

