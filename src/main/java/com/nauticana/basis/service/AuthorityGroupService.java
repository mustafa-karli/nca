package com.nauticana.basis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.AuthorityGroup;


@Service
public class AuthorityGroupService extends AbstractService<AuthorityGroup, String> {

	@Override
	@Transactional
	public AuthorityGroup newEntityWithParentId(String parentKey) {
		return new AuthorityGroup();
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public AuthorityGroup newEntityWithId(String strId) {
		AuthorityGroup entity = new AuthorityGroup();
		entity.setId(strId);
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<AuthorityGroup> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(AuthorityGroup x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(AuthorityGroup entity) {
		return null;
	}

	@Override
	public String idAsStr(AuthorityGroup entity) {
		if (entity != null)
			return entity.getId();
		else
			return null;
	}

}
