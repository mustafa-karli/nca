package com.nauticana.maintenance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.maintenance.model.ServiceCharge;
import com.nauticana.maintenance.model.ServiceChargeId;
import com.nauticana.maintenance.model.ServiceType;

@Service
public class ServiceChargeService extends AbstractService<ServiceCharge, ServiceChargeId> {

	@Autowired
	ServiceTypeService parentService;

	@Override
	@Transactional
	public ServiceCharge newEntityWithParentId(String parentKey) {
		ServiceCharge entity = new ServiceCharge();
		entity.setId(new ServiceChargeId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setServiceTypeId(parentId);
		return entity;
	}

	@Override
	public ServiceChargeId strToId(String id) {
		return new ServiceChargeId(id);
	}

	@Override
	public ServiceCharge newEntityWithId(String strId) {
		ServiceCharge entity = new ServiceCharge();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ServiceCharge entity) {
		if (entity == null) return null;
		return ServiceType.ROOTMAPPING + "/show?id=" + entity.getId().getServiceTypeId();
	}

	@Override
	public String idAsStr(ServiceCharge entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}

}

