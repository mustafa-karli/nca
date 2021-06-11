package com.nauticana.basis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.TableViewScenarioItem;
import com.nauticana.basis.model.TableViewScenarioItemId;
import com.nauticana.basis.repository.TableViewScenarioRepository;
import com.nauticana.basis.utils.Utils;

@Service
public class TableViewScenarioItemService extends AbstractService<TableViewScenarioItem, TableViewScenarioItemId> {

	@Autowired
	TableViewScenarioRepository parentRep;

	@Override
	public TableViewScenarioItem newEntityWithParentId(String parentKey) {
		TableViewScenarioItem entity = new TableViewScenarioItem();
		if (!Utils.emptyStr(parentKey)) {
			TableViewScenarioItemId id = new TableViewScenarioItemId();
			int parentId = Integer.parseInt(parentKey);
			id.setScenarioId(parentId);
			entity.setTableViewScenario(parentRep.findById(parentId).get());
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public TableViewScenarioItemId strToId(String id) {
		return new TableViewScenarioItemId(id);
	}

	@Override
	public TableViewScenarioItem newEntityWithId(String strId) {
		TableViewScenarioItem entity = new TableViewScenarioItem();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(TableViewScenarioItem entity) {
		if (entity == null) return null;
		return "tableViewScenario/show?id=" + entity.getId().getScenarioId();
	}

	@Override
	public String idAsStr(TableViewScenarioItem entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}
