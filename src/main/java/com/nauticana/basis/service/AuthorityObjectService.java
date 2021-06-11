package com.nauticana.basis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.AuthorityObject;


@Service
public class AuthorityObjectService extends AbstractService<AuthorityObject, String> {

	@Override
	@Transactional
	public AuthorityObject newEntityWithParentId(String parentKey) {
		return new AuthorityObject();
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public AuthorityObject newEntityWithId(String strId) {
		AuthorityObject entity = new AuthorityObject();
		entity.setId(strId);
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<AuthorityObject> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(AuthorityObject x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getId();
		}
		return items;
	}

	@Override
	public String parentLink(AuthorityObject entity) {
		return null;
	}

	@Override
	public String idAsStr(AuthorityObject entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}
