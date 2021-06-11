package com.nauticana.maintenance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.maintenance.model.Equipment;
import com.nauticana.maintenance.model.EquipmentId;
import com.nauticana.maintenance.model.Maintenance;
import com.nauticana.maintenance.model.MaintenanceId;

@Service
public class MaintenanceService extends AbstractService<Maintenance, MaintenanceId> {

	@Autowired
	EquipmentService parentService;

	@Override
	@Transactional
	public Maintenance newEntityWithParentId(String parentKey) {
		Maintenance entity = new Maintenance();
		entity.setId(new MaintenanceId());
		EquipmentId parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId.getMaterialId());
		entity.getId().setSerialNo(parentId.getSerialNo());
		entity.getId().setServiceDate(Utils.onlyDate());
		entity.setEquipment(parentService.findById(parentId));
		return entity;
	}

	@Override
	public MaintenanceId strToId(String id) {
		return new MaintenanceId(id);
	}

	@Override
	public Maintenance newEntityWithId(String strId) {
		Maintenance entity = new Maintenance();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(Maintenance entity) {
		if (entity == null) return null;
		return Equipment.ROOTMAPPING + "/show?id=" + entity.getEquipment().getId().toString();
	}

	@Override
	public String idAsStr(Maintenance entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}

