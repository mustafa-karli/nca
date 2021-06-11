package com.nauticana.helpdesk.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.helpdesk.model.SupportArea;

@Service
public class SupportAreaService extends AbstractService<SupportArea, Integer> {

	@Override
	@Transactional
	public SupportArea newEntityWithParentId(String parentKey) {
		SupportArea entity = new SupportArea();
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public SupportArea newEntityWithId(String strId) {
		SupportArea entity = new SupportArea();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};

	@Override
	public void setClient(SupportArea entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(SupportArea entity) {
		return entity.getOwnerId();
	};

	@Override
	public String[][] findAllForLookup(int client) {
		List<SupportArea> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(SupportArea x : list) {
			i++;
			items[i][0] = x.getId()+"";
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(SupportArea entity) {
		return null;
	}

	@Override
	public String idAsStr(SupportArea entity) {
		if (entity == null) return null;
		return entity.getId() + "";
	}
}

