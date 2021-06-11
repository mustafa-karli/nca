package com.nauticana.business.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.business.model.BusinessPartner;

@Service
public class BusinessPartnerService extends AbstractService<BusinessPartner, Integer> {

	@Override
	@Transactional
	public BusinessPartner newEntityWithParentId(String parentKey) {
		BusinessPartner entity = new BusinessPartner();
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public BusinessPartner newEntityWithId(String strId) {
		BusinessPartner entity = new BusinessPartner();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<BusinessPartner> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(BusinessPartner x : list) {
			i++;
			items[i][0] = x.getId() + "";
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(BusinessPartner entity) {
		return null;
	}

	@Override
	public String idAsStr(BusinessPartner entity) {
		if (entity == null) return null;
		return entity.getId() + "";
	}
}

