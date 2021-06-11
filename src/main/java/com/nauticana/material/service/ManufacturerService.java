package com.nauticana.material.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.Manufacturer;

@Service
public class ManufacturerService extends AbstractService<Manufacturer, String> {

	@Override
	@Transactional
	public Manufacturer newEntityWithParentId(String parentKey) {
		Manufacturer entity = new Manufacturer();
		return entity;
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public Manufacturer newEntityWithId(String strId) {
		Manufacturer entity = new Manufacturer();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<Manufacturer> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Manufacturer x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(Manufacturer entity) {
		return null;
	}

	@Override
	public String idAsStr(Manufacturer entity) {
		if (entity != null)
			return entity.getId();
		else
			return null;
	}
}

