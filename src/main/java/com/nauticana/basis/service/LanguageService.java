package com.nauticana.basis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.Language;

@Service
public class LanguageService  extends AbstractService<Language,String> {
	
	@Override
	public Language newEntityWithParentId(String parentKey) {
		return new Language();
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public Language newEntityWithId(String strId) {
		Language entity = new Language();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<Language> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Language x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
	}

	public String[][]  findAllFlag() {
		List<Language> list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Language x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = "flag-icon " + x.getFlag();
		}
		return items;
	}

	@Override
	public String parentLink(Language entity) {
		return null;
	}

	@Override
	public String idAsStr(Language entity) {
		if (entity != null)
			return entity.getId();
		else
			return null;
	}

}
