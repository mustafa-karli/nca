package com.nauticana.material.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.LotInventory;
import com.nauticana.material.model.LotInventoryId;
import com.nauticana.material.model.MaterialInventory;
import com.nauticana.material.model.MaterialInventoryId;

@Service
public class LotInventoryService extends AbstractService<LotInventory, LotInventoryId> {

	@Autowired
	MaterialInventoryService parentService;

	@Override
	@Transactional
	public LotInventory newEntityWithParentId(String parentKey) {
		LotInventory entity = new LotInventory();
		MaterialInventoryId parentId = parentService.strToId(parentKey);
		LotInventoryId id = new LotInventoryId();
		id.setMaterialId(parentId.getMaterialId());
		id.setOrganizationId(parentId.getOrganizationId());
		entity.setId(id);
		entity.setMaterialInventory(parentService.findById(parentService.strToId(parentKey)));
		return entity;
	}

	@Override
	public LotInventoryId strToId(String id) {
		return new LotInventoryId(id);
	}

	@Override
	public LotInventory newEntityWithId(String strId) {
		LotInventory entity = new LotInventory();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(LotInventory entity) {
		if (entity == null) return null;
		return MaterialInventory.ROOTMAPPING + "/show?id=" + entity.getMaterialInventory().getId().toString();
	}

	@Override
	public String idAsStr(LotInventory entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

