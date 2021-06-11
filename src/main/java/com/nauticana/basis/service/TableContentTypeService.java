package com.nauticana.basis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.TableContentType;
import com.nauticana.basis.model.TableContentTypeId;
import com.nauticana.basis.repository.TableControllerStaticRepository;
import com.nauticana.basis.utils.Utils;

@Service
public class TableContentTypeService extends AbstractService<TableContentType, TableContentTypeId> {

	@Autowired
	TableControllerStaticRepository parentRep;

	@Override
	public TableContentType newEntityWithParentId(String parentKey) {
		TableContentType entity = new TableContentType();
		if (!Utils.emptyStr(parentKey)) {
			TableContentTypeId id = new TableContentTypeId();
			id.setTablename(parentKey);
			entity.setTableControllerStatic(parentRep.findById(parentKey).get());
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public TableContentTypeId strToId(String id) {
		return new TableContentTypeId(id);
	}

	@Override
	public TableContentType newEntityWithId(String strId) {
		TableContentType entity = new TableContentType();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(TableContentType entity) {
		if (entity == null) return null;
		return "tableControllerStatic/show?id=" + entity.getId().getTablename();
	}

	@Override
	public String idAsStr(TableContentType entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}
