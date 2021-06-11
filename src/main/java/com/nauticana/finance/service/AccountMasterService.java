package com.nauticana.finance.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.finance.model.AccountMaster;

@Service
public class AccountMasterService extends AbstractService<AccountMaster, String> {

	@Override
	@Transactional
	public AccountMaster newEntityWithParentId(String parentKey) {
		AccountMaster entity = new AccountMaster();
		return entity;
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public AccountMaster newEntityWithId(String strId) {
		AccountMaster entity = new AccountMaster();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<AccountMaster> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(AccountMaster x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(AccountMaster entity) {
		return null;
	}

	@Override
	public String idAsStr(AccountMaster entity) {
		if (entity == null) return null;
		return entity.getId();
	}
}

