package com.nauticana.maintenance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.maintenance.model.Equipment;
import com.nauticana.maintenance.model.EquipmentAssignment;
import com.nauticana.maintenance.model.EquipmentAssignmentId;
import com.nauticana.maintenance.model.EquipmentId;

@Service
public class EquipmentAssignmentService extends AbstractService<EquipmentAssignment, EquipmentAssignmentId> {

	@Autowired
	EquipmentService parentService;

	@Override
	@Transactional
	public EquipmentAssignment newEntityWithParentId(String parentKey) {
		EquipmentAssignment entity = new EquipmentAssignment();
		entity.setId(new EquipmentAssignmentId());
		EquipmentId parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId.getMaterialId());
		entity.getId().setSerialNo(parentId.getSerialNo());
		entity.getId().setBegda(Utils.onlyDate());
		entity.setEquipment(parentService.findById(parentId));
		return entity;
	}

	@Override
	public EquipmentAssignmentId strToId(String id) {
		return new EquipmentAssignmentId(id);
	}

	@Override
	public EquipmentAssignment newEntityWithId(String strId) {
		EquipmentAssignment entity = new EquipmentAssignment();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(EquipmentAssignment entity) {
		if (entity == null) return null;
		return Equipment.ROOTMAPPING + "/show?id=" + entity.getEquipment().getId().toString();
	}

	@Override
	public String idAsStr(EquipmentAssignment entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}

