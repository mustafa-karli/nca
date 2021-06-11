package com.nauticana.material.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.MaterialInventory;
import com.nauticana.material.model.MaterialInventoryId;

@Service
public class MaterialInventoryService extends AbstractService<MaterialInventory, MaterialInventoryId> {

	@Autowired
	MaterialService parentService;

	@Override
	@Transactional
	public MaterialInventory newEntityWithParentId(String parentKey) {
		MaterialInventory entity = new MaterialInventory();
		entity.setId(new MaterialInventoryId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId);
		entity.setMaterial(parentService.findById(parentId));
		return entity;
	}

	@Override
	public MaterialInventoryId strToId(String id) {
		return new MaterialInventoryId(id);
	}

	@Override
	public MaterialInventory newEntityWithId(String strId) {
		MaterialInventory entity = new MaterialInventory();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaterialInventory entity) {
		if (entity == null) return null;
		return "material/show?id=" + entity.getId().getMaterialId();
	}

	@Override
	public String idAsStr(MaterialInventory entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

