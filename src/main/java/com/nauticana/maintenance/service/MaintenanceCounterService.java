package com.nauticana.maintenance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.maintenance.model.Maintenance;
import com.nauticana.maintenance.model.MaintenanceCounter;
import com.nauticana.maintenance.model.MaintenanceCounterId;
import com.nauticana.maintenance.model.MaintenanceId;

@Service
public class MaintenanceCounterService extends AbstractService<MaintenanceCounter, MaintenanceCounterId> {

	@Autowired
	MaintenanceService parentService;

	@Override
	@Transactional
	public MaintenanceCounter newEntityWithParentId(String parentKey) {
		MaintenanceCounter entity = new MaintenanceCounter();
		entity.setId(new MaintenanceCounterId());
		MaintenanceId parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId.getMaterialId());
		entity.getId().setSerialNo(parentId.getSerialNo());
		entity.getId().setServiceDate(parentId.getServiceDate());
		entity.setMaintenance(parentService.findById(parentId));
		return entity;
	}

	@Override
	public MaintenanceCounterId strToId(String id) {
		return new MaintenanceCounterId(id);
	}

	@Override
	public MaintenanceCounter newEntityWithId(String strId) {
		MaintenanceCounter entity = new MaintenanceCounter();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaintenanceCounter entity) {
		if (entity == null) return null;
		return Maintenance.ROOTMAPPING + "/show?id=" + entity.getMaintenance().getId().toString();
	}

	@Override
	public String idAsStr(MaintenanceCounter entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}

}

