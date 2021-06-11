package com.nauticana.maintenance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.maintenance.model.FinalEquipmentPart;
import com.nauticana.maintenance.model.FinalEquipmentPartId;
import com.nauticana.material.service.MaterialService;

@Service
public class FinalEquipmentPartService extends AbstractService<FinalEquipmentPart, FinalEquipmentPartId> {

	@Autowired
	MaterialService parentService;

	@Override
	@Transactional
	public FinalEquipmentPart newEntityWithParentId(String parentKey) {
		FinalEquipmentPart entity = new FinalEquipmentPart();
		entity.setId(new FinalEquipmentPartId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId);
		entity.setMaterial(parentService.findById(parentId));
		return entity;
	}

	@Override
	public FinalEquipmentPartId strToId(String id) {
		return new FinalEquipmentPartId(id);
	}

	@Override
	public FinalEquipmentPart newEntityWithId(String strId) {
		FinalEquipmentPart entity = new FinalEquipmentPart();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(FinalEquipmentPart entity) {
		if (entity == null) return null;
		return "equipment/show?id=" + entity.getId().getMaterialId();
	}

	@Override
	public String idAsStr(FinalEquipmentPart entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}

