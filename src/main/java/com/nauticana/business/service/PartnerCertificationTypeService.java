package com.nauticana.business.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.business.model.PartnerCertificationType;

@Service
public class PartnerCertificationTypeService extends AbstractService<PartnerCertificationType, String> {

	@Override
	@Transactional
	public PartnerCertificationType newEntityWithParentId(String parentKey) {
		PartnerCertificationType entity = new PartnerCertificationType();
		return entity;
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public PartnerCertificationType newEntityWithId(String strId) {
		PartnerCertificationType entity = new PartnerCertificationType();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<PartnerCertificationType> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(PartnerCertificationType x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(PartnerCertificationType entity) {
		return null;
	}

	@Override
	public String idAsStr(PartnerCertificationType entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}

