package com.nauticana.maintenance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.maintenance.model.MxCounterInterval;
import com.nauticana.maintenance.model.MxCounterIntervalId;
import com.nauticana.maintenance.model.MxCounterType;

@Service
public class MxCounterIntervalService extends AbstractService<MxCounterInterval, MxCounterIntervalId> {

	@Autowired
	MxCounterTypeService parentService;

	@Override
	@Transactional
	public MxCounterInterval newEntityWithParentId(String parentKey) {
		MxCounterInterval entity = new MxCounterInterval();
		entity.setId(new MxCounterIntervalId());
		entity.getId().setCounterType(parentKey);
		entity.setMxCounterType(parentService.findById(parentKey));
		return entity;
	}

	@Override
	public MxCounterIntervalId strToId(String id) {
		return new MxCounterIntervalId(id);
	}

	@Override
	public MxCounterInterval newEntityWithId(String strId) {
		MxCounterInterval entity = new MxCounterInterval();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MxCounterInterval entity) {
		if (entity == null) return null;
		return MxCounterType.ROOTMAPPING + "/show?id=" + entity.getId().getCounterType();
	}

	@Override
	public String idAsStr(MxCounterInterval entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}

