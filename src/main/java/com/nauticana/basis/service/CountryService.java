package com.nauticana.basis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.Country;

@Service
public class CountryService extends AbstractService<Country,String> {
		
	@Override
	public Country newEntityWithParentId(String parentKey) {
		return new Country();
	}
	
	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public Country newEntityWithId(String strId) {
		Country entity = new Country();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<Country> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Country x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(Country entity) {
		return null;
	}

	@Override
	public String idAsStr(Country entity) {
		if (entity != null)
			return entity.getId();
		else
			return null;
	}
}
