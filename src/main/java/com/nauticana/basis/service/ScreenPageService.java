package com.nauticana.basis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.MainMenu;
import com.nauticana.basis.model.ScreenPage;
import com.nauticana.basis.repository.MainMenuRepository;
import com.nauticana.basis.utils.Utils;

@Service
public class ScreenPageService extends AbstractService<ScreenPage, String> {

	@Autowired
	MainMenuRepository parentRep;
	
	@Override
	public ScreenPage newEntityWithParentId(String parentKey) {
		ScreenPage entity = new ScreenPage();
		if (!Utils.emptyStr(parentKey)) {
			entity.setMenu(parentRep.findById(parentKey).get());
		}
		return entity;
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public ScreenPage newEntityWithId(String strId) {
		ScreenPage entity = new ScreenPage();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<ScreenPage> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(ScreenPage x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(ScreenPage entity) {
		if (entity == null) return null;
		if (entity.getMenu() == null) return null;
		return MainMenu.ROOTMAPPING + "/show?id=" + entity.getMenu().getId();
	}

	@Override
	public String idAsStr(ScreenPage entity) {
		if (entity == null) return null;
		return entity.getId();
	}

}
