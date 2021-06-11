package com.nauticana.maintenance.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.maintenance.model.MxCounterType;

@Service
public class MxCounterTypeService extends AbstractService<MxCounterType, String> {

	@Override
	@Transactional
	public MxCounterType newEntityWithParentId(String parentKey) {
		MxCounterType entity = new MxCounterType();
		return entity;
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public MxCounterType newEntityWithId(String strId) {
		MxCounterType entity = new MxCounterType();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<MxCounterType> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(MxCounterType x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(MxCounterType entity) {
		return null;
	}

	@Override
	public String idAsStr(MxCounterType entity) {
		if (entity == null) return null;
		return entity.getId();
	}
}

