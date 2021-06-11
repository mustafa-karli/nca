package com.nauticana.material.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.MaterialPackUnit;
import com.nauticana.material.model.MaterialPackUnitId;

@Service
public class MaterialPackUnitService extends AbstractService<MaterialPackUnit, MaterialPackUnitId> {

	@Autowired
	MaterialService parentService;

	@Override
	@Transactional
	public MaterialPackUnit newEntityWithParentId(String parentKey) {
		MaterialPackUnit entity = new MaterialPackUnit();
		entity.setId(new MaterialPackUnitId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId);
		entity.setMaterial(parentService.findById(parentId));
		return entity;
	}

	@Override
	public MaterialPackUnitId strToId(String id) {
		return new MaterialPackUnitId(id);
	}

	@Override
	public MaterialPackUnit newEntityWithId(String strId) {
		MaterialPackUnit entity = new MaterialPackUnit();
		entity.setId(strToId(strId));
		return entity;
	}
	
	public String parentLink(MaterialPackUnit entity) {
		if (entity == null) return null;
		return "material/show?id=" + entity.getId().getMaterialId();
	}

	@Override
	public String idAsStr(MaterialPackUnit entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

