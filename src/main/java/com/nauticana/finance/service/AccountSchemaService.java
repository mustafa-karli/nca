package com.nauticana.finance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.finance.model.AccountSchema;

@Service
public class AccountSchemaService  extends AbstractService<AccountSchema, Integer> {
	
	@Override
	public AccountSchema newEntityWithParentId(String parentKey) {
		return new AccountSchema();
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};

	@Override
	public void setClient(AccountSchema entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(AccountSchema entity) {
		return entity.getOwnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public AccountSchema newEntityWithId(String strId) {
		AccountSchema entity = new AccountSchema();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<AccountSchema> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(AccountSchema x : list) {
			i++;
			items[i][0] = x.getId()+"";
			items[i][1] = x.getId() + " " + x.getCaption();
		}
		return items;
	}
//	public Map<String, String> findAllStr() {
//		List<AccountSchema> list = findAll();
//		Map<String, String> map = new TreeMap<String, String>();
//		map.put(null, " - ");
//		for(AccountSchema x : list) {
//			map.put(x.getId(), x.getId() + " " + x.getCaption());
//		}
//		return map;
//	}

	@Override
	public String parentLink(AccountSchema entity) {
		return null;
	}

	@Override
	public String idAsStr(AccountSchema entity) {
		if (entity == null) return null;
		return entity.getId() + "";
	}
}
