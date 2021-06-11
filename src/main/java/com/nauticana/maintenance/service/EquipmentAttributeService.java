package com.nauticana.maintenance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.maintenance.model.Equipment;
import com.nauticana.maintenance.model.EquipmentAttribute;
import com.nauticana.maintenance.model.EquipmentAttributeId;
import com.nauticana.maintenance.model.EquipmentId;

@Service
public class EquipmentAttributeService extends AbstractService<EquipmentAttribute, EquipmentAttributeId> {

	@Autowired
	EquipmentService parentService;

	@Override
	@Transactional
	public EquipmentAttribute newEntityWithParentId(String parentKey) {
		EquipmentAttribute entity = new EquipmentAttribute();
		entity.setId(new EquipmentAttributeId());
		EquipmentId parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId.getMaterialId());
		entity.getId().setSerialNo(parentId.getSerialNo());
		entity.setEquipment(parentService.findById(parentId));
		return entity;
	}

	@Override
	public EquipmentAttributeId strToId(String id) {
		return new EquipmentAttributeId(id);
	}

	@Override
	public EquipmentAttribute newEntityWithId(String strId) {
		EquipmentAttribute entity = new EquipmentAttribute();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(EquipmentAttribute entity) {
		if (entity == null) return null;
		return Equipment.ROOTMAPPING + "/show?id=" + entity.getEquipment().getId().toString();
	}

	@Override
	public String idAsStr(EquipmentAttribute entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}

