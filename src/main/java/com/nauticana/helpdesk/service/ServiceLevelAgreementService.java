package com.nauticana.helpdesk.service;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.business.service.BusinessPartnerService;
import com.nauticana.helpdesk.model.ServiceLevelAgreement;
import com.nauticana.helpdesk.model.ServiceLevelAgreementId;

@Service
public class ServiceLevelAgreementService extends AbstractService<ServiceLevelAgreement, ServiceLevelAgreementId> {

	@Autowired
	BusinessPartnerService parentService;

	@Override
	@Transactional
	public ServiceLevelAgreement newEntityWithParentId(String parentKey) {
		ServiceLevelAgreement entity = new ServiceLevelAgreement();
		return entity;
	}

	@Override
	public ServiceLevelAgreementId strToId(String id) {
		return new ServiceLevelAgreementId(id);
	}

	@Override
	public ServiceLevelAgreement newEntityWithId(String strId) {
		ServiceLevelAgreement entity = new ServiceLevelAgreement();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};

	@Override
	public void setClient(ServiceLevelAgreement entity, int client) {
		entity.getId().setOwnerId(client);
	};

	@Override
	public int getClient(ServiceLevelAgreement entity) {
		return entity.getId().getOwnerId();
	};

	@Override
	public String parentLink(ServiceLevelAgreement entity) {
		return null;
	}

	@Override
	public String idAsStr(ServiceLevelAgreement entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}