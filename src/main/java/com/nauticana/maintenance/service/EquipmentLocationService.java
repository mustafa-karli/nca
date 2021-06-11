package com.nauticana.maintenance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.maintenance.model.Equipment;
import com.nauticana.maintenance.model.EquipmentId;
import com.nauticana.maintenance.model.EquipmentLocation;
import com.nauticana.maintenance.model.EquipmentLocationId;

@Service
public class EquipmentLocationService extends AbstractService<EquipmentLocation, EquipmentLocationId> {

	@Autowired
	EquipmentService parentService;

	@Override
	@Transactional
	public EquipmentLocation newEntityWithParentId(String parentKey) {
		EquipmentLocation entity = new EquipmentLocation();
		entity.setId(new EquipmentLocationId());
		EquipmentId parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId.getMaterialId());
		entity.getId().setSerialNo(parentId.getSerialNo());
		entity.setEquipment(parentService.findById(parentId));
		return entity;
	}

	@Override
	public EquipmentLocationId strToId(String id) {
		return new EquipmentLocationId(id);
	}

	@Override
	public EquipmentLocation newEntityWithId(String strId) {
		EquipmentLocation entity = new EquipmentLocation();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(EquipmentLocation entity) {
		if (entity == null) return null;
		return Equipment.ROOTMAPPING + "/show?id=" + entity.getEquipment().getId().toString();
	}

	@Override
	public String idAsStr(EquipmentLocation entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}

