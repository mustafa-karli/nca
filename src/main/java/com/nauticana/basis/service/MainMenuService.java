package com.nauticana.basis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.MainMenu;

@Service
public class MainMenuService extends AbstractService<MainMenu, String> {

	@Override
	public MainMenu newEntityWithParentId(String parentKey) {
		return new MainMenu();
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public MainMenu newEntityWithId(String strId) {
		MainMenu entity = new MainMenu();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<MainMenu> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(MainMenu x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
//		Map<String, String> map = new HashMap<String, String>();
//		map.put(null, " - ");
//		for(MainMenu x : list) {
//			map.put(x.getId(), x.getCaption());
//		}
//		return map;

	}

	@Override
	public String parentLink(MainMenu entity) {
		return null;
	}

	@Override
	public String idAsStr(MainMenu entity) {
		if (entity != null)
			return entity.getId();
		else
			return null;
	}

}
