package com.nauticana.business.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.business.model.BusinessService;

@Service
public class BusinessServiceService extends AbstractService<BusinessService, String> {

	@Override
	@Transactional
	public BusinessService newEntityWithParentId(String parentKey) {
		BusinessService entity = new BusinessService();
		return entity;
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public BusinessService newEntityWithId(String strId) {
		BusinessService entity = new BusinessService();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<BusinessService> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(BusinessService x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(BusinessService entity) {
		return null;
	}

	@Override
	public String idAsStr(BusinessService entity) {
		if (entity == null) return null;
		return entity.getId();
	}
}

