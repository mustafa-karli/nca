package com.nauticana.basis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.MasterDetailRelation;
import com.nauticana.basis.repository.TableControllerStaticRepository;
import com.nauticana.basis.utils.Utils;

@Service
public class MasterDetailRelationService extends AbstractService<MasterDetailRelation, String> {

	@Autowired
	TableControllerStaticRepository parentRep;

	@Override
	public MasterDetailRelation newEntityWithParentId(String parentKey) {
		MasterDetailRelation entity = new MasterDetailRelation();
		if (!Utils.emptyStr(parentKey)) {
			entity.setMasterTableController(parentRep.findById(parentKey).get());
		}
		return entity;
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public MasterDetailRelation newEntityWithId(String strId) {
		MasterDetailRelation entity = new MasterDetailRelation();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MasterDetailRelation entity) {
		if (entity == null) return null;
		return "tableControllerStatic/show?id=" + entity.getMasterTableController().getId();
	}

	@Override
	public String idAsStr(MasterDetailRelation entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}
