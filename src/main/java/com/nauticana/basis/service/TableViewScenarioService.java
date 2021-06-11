package com.nauticana.basis.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.TableViewScenario;

@Service
public class TableViewScenarioService extends AbstractService<TableViewScenario, Integer> {

	@Override
	@Transactional
	public TableViewScenario newEntityWithParentId(String parentKey) {
		TableViewScenario entity = new TableViewScenario();
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public TableViewScenario newEntityWithId(String strId) {
		TableViewScenario entity = new TableViewScenario();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<TableViewScenario> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(TableViewScenario x : list) {
			i++;
			items[i][0] = x.getId() + "";
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(TableViewScenario entity) {
		return null;
	}

	@Override
	public String idAsStr(TableViewScenario entity) {
		if (entity == null) return null;
		return entity.getId() + "";
	}
}

