package com.nauticana.basis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.DomainName;

@Service
public class DomainNameService extends AbstractService<DomainName,String> {
		
	@Override
	public DomainName newEntityWithParentId(String parentKey) {
		return new DomainName();
	}
	
	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public DomainName newEntityWithId(String strId) {
		DomainName entity = new DomainName();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<DomainName> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(DomainName x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getId() + " " + x.getId();
		}
		return items;
	}

	@Override
	public String parentLink(DomainName entity) {
		return null;
	}

	@Override
	public String idAsStr(DomainName entity) {
		if (entity != null)
			return entity.getId();
		else
			return null;
	}
}
