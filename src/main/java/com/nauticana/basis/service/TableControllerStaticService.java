package com.nauticana.basis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.TableControllerStatic;

@Service
public class TableControllerStaticService extends AbstractService<TableControllerStatic, String> {

	@Override
	public TableControllerStatic newEntityWithParentId(String parentKey) {
		return new TableControllerStatic();
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public TableControllerStatic newEntityWithId(String strId) {
		TableControllerStatic entity = new TableControllerStatic();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(TableControllerStatic entity) {
		return null;
	}
	
	@Override
	public String[][] findAllForLookup(int client) {
		List<TableControllerStatic> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(TableControllerStatic x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getId();
		}
		return items;
	}

	@Override
	public String idAsStr(TableControllerStatic entity) {
		if (entity == null) return null;
		return entity.getId();
	}
	
}
