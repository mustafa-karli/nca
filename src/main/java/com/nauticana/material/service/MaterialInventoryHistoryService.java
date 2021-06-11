package com.nauticana.material.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.MaterialInventoryHistory;
import com.nauticana.material.model.MaterialInventoryHistoryId;

@Service
public class MaterialInventoryHistoryService extends AbstractService<MaterialInventoryHistory, MaterialInventoryHistoryId> {

	@Autowired
	MaterialService parentService;

	@Override
	@Transactional
	public MaterialInventoryHistory newEntityWithParentId(String parentKey) {
		MaterialInventoryHistory entity = new MaterialInventoryHistory();
		entity.setId(new MaterialInventoryHistoryId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId);
		entity.setMaterial(parentService.findById(parentId));
		return entity;
	}

	@Override
	public MaterialInventoryHistoryId strToId(String id) {
		return new MaterialInventoryHistoryId(id);
	}

	@Override
	public MaterialInventoryHistory newEntityWithId(String strId) {
		MaterialInventoryHistory entity = new MaterialInventoryHistory();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaterialInventoryHistory entity) {
		if (entity == null) return null;
		return "material/show?id=" + entity.getId().getMaterialId();
	}

	@Override
	public String idAsStr(MaterialInventoryHistory entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

