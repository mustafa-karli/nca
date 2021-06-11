package com.nauticana.basis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.TableFieldFace;
import com.nauticana.basis.model.TableFieldFaceId;
import com.nauticana.basis.repository.TableControllerStaticRepository;
import com.nauticana.basis.utils.Utils;

@Service
public class TableFieldFaceService extends AbstractService<TableFieldFace, TableFieldFaceId> {

	@Autowired
	TableControllerStaticRepository parentRep;

	@Override
	public TableFieldFace newEntityWithParentId(String parentKey) {
		TableFieldFace entity = new TableFieldFace();
		if (!Utils.emptyStr(parentKey)) {
			TableFieldFaceId id = new TableFieldFaceId();
			id.setTablename(parentKey);
			entity.setTableControllerStatic(parentRep.findById(parentKey).get());
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public TableFieldFaceId strToId(String id) {
		return new TableFieldFaceId(id);
	}

	@Override
	public TableFieldFace newEntityWithId(String strId) {
		TableFieldFace entity = new TableFieldFace();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(TableFieldFace entity) {
		if (entity == null) return null;
		return "tableControllerStatic/show?id=" + entity.getId().getTablename();
	}

	@Override
	public String idAsStr(TableFieldFace entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}
