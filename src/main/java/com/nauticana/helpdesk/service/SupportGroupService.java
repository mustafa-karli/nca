package com.nauticana.helpdesk.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.helpdesk.model.SupportGroup;

@Service
public class SupportGroupService extends AbstractService<SupportGroup, Integer> {

	@Override
	@Transactional
	public SupportGroup newEntityWithParentId(String parentKey) {
		SupportGroup entity = new SupportGroup();
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public SupportGroup newEntityWithId(String strId) {
		SupportGroup entity = new SupportGroup();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};

	@Override
	public void setClient(SupportGroup entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(SupportGroup entity) {
		return entity.getOwnerId();
	};

	@Override
	public String[][] findAllForLookup(int client) {
		List<SupportGroup> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(SupportGroup x : list) {
			i++;
			items[i][0] = x.getId()+"";
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(SupportGroup entity) {
		return null;
	}

	@Override
	public String idAsStr(SupportGroup entity) {
		if (entity == null) return null;
		return entity.getId() + "";
	}
}