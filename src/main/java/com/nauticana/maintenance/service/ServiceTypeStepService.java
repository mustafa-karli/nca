package com.nauticana.maintenance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.maintenance.model.ServiceType;
import com.nauticana.maintenance.model.ServiceTypeStep;
import com.nauticana.maintenance.model.ServiceTypeStepId;

@Service
public class ServiceTypeStepService extends AbstractService<ServiceTypeStep, ServiceTypeStepId> {

	@Autowired
	ServiceTypeService parentService;

	@Override
	@Transactional
	public ServiceTypeStep newEntityWithParentId(String parentKey) {
		ServiceTypeStep entity = new ServiceTypeStep();
		entity.setId(new ServiceTypeStepId());
		entity.getId().setServiceTypeId(parentKey);
		entity.setServiceType(parentService.findById(parentService.strToId(parentKey)));
		return entity;
	}

	@Override
	public ServiceTypeStepId strToId(String id) {
		return new ServiceTypeStepId(id);
	}

	@Override
	public ServiceTypeStep newEntityWithId(String strId) {
		ServiceTypeStep entity = new ServiceTypeStep();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ServiceTypeStep entity) {
		if (entity == null) return null;
		return ServiceType.ROOTMAPPING + "/show?id=" + entity.getId().getServiceTypeId();
	}

	@Override
	public String idAsStr(ServiceTypeStep entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}

}

