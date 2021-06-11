package com.nauticana.maintenance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.maintenance.model.Maintenance;
import com.nauticana.maintenance.model.MaintenanceId;
import com.nauticana.maintenance.model.MaintenanceStep;
import com.nauticana.maintenance.model.MaintenanceStepId;

@Service
public class MaintenanceStepService extends AbstractService<MaintenanceStep, MaintenanceStepId> {

	@Autowired
	MaintenanceService parentService;

	@Override
	@Transactional
	public MaintenanceStep newEntityWithParentId(String parentKey) {
		MaintenanceStep entity = new MaintenanceStep();
		entity.setId(new MaintenanceStepId());
		MaintenanceId parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId.getMaterialId());
		entity.getId().setSerialNo(parentId.getSerialNo());
		entity.getId().setServiceDate(parentId.getServiceDate());
		entity.setMaintenance(parentService.findById(parentId));
		return entity;
	}

	@Override
	public MaintenanceStepId strToId(String id) {
		return new MaintenanceStepId(id);
	}

	@Override
	public MaintenanceStep newEntityWithId(String strId) {
		MaintenanceStep entity = new MaintenanceStep();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaintenanceStep entity) {
		if (entity == null) return null;
		return Maintenance.ROOTMAPPING + "/show?id=" + entity.getMaintenance().getId().toString();
	}

	@Override
	public String idAsStr(MaintenanceStep entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}
