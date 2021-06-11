package com.nauticana.basis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.TableAction;
import com.nauticana.basis.model.TableActionId;
import com.nauticana.basis.repository.TableControllerStaticRepository;
import com.nauticana.basis.utils.Utils;

@Service
public class TableActionService extends AbstractService<TableAction, TableActionId> {

	@Autowired
	TableControllerStaticRepository parentRep;

	@Override
	public TableAction newEntityWithParentId(String parentKey) {
		TableAction entity = new TableAction();
		if (!Utils.emptyStr(parentKey)) {
			TableActionId id = new TableActionId();
			id.setTablename(parentKey);
			entity.setTableControllerStatic(parentRep.findById(parentKey).get());
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public TableActionId strToId(String id) {
		return new TableActionId(id);
	}

	@Override
	public TableAction newEntityWithId(String strId) {
		TableAction entity = new TableAction();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(TableAction entity) {
		if (entity == null) return null;
		return "tableControllerStatic/show?id=" + entity.getId().getTablename();
	}

	@Override
	public String idAsStr(TableAction entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}
