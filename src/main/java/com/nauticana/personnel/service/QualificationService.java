package com.nauticana.personnel.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.personnel.model.Qualification;

@Service
public class QualificationService extends AbstractService<Qualification, Integer> {

	@Override
	@Transactional
	public Qualification newEntityWithParentId(String parentKey) {
		Qualification entity = new Qualification();
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public Qualification newEntityWithId(String strId) {
		Qualification entity = new Qualification();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<Qualification> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Qualification x : list) {
			i++;
			items[i][0] = x.getId() + "";
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(Qualification entity) {
		return null;
	}

	@Override
	public String idAsStr(Qualification entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}
}

