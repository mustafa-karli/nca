package com.nauticana.business.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.business.model.PartnerCertification;

@Service
public class PartnerCertificationService extends AbstractService<PartnerCertification, Integer> {

	@Autowired
	BusinessPartnerService parentService;

	@Override
	@Transactional
	public PartnerCertification newEntityWithParentId(String parentKey) {
		PartnerCertification entity = new PartnerCertification();
		if (!Utils.emptyStr(parentKey)) {
			entity.setBusinessPartner(parentService.findById(parentService.strToId(parentKey)));
		}
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public PartnerCertification newEntityWithId(String strId) {
		PartnerCertification entity = new PartnerCertification();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		return null;
	}

	@Override
	public String parentLink(PartnerCertification entity) {
		if (entity == null) return null;
		return "businessPartner/show?id=" + entity.getBusinessPartner().getId();
	}

	@Override
	public String idAsStr(PartnerCertification entity) {
		if (entity == null) return null;
		return entity.getId() + "";
	}
}

