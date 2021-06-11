package com.nauticana.finance.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.finance.model.AccountTransactionTemplate;

@Service
public class AccountTransactionTemplateService extends AbstractService<AccountTransactionTemplate, Integer> {

	@Override
	@Transactional
	public AccountTransactionTemplate newEntityWithParentId(String parentKey) {
		AccountTransactionTemplate entity = new AccountTransactionTemplate();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};

	@Override
	public void setClient(AccountTransactionTemplate entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(AccountTransactionTemplate entity) {
		return entity.getOwnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public AccountTransactionTemplate newEntityWithId(String strId) {
		AccountTransactionTemplate entity = new AccountTransactionTemplate();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<AccountTransactionTemplate> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(AccountTransactionTemplate x : list) {
			i++;
			items[i][0] = x.getId()+"";
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(AccountTransactionTemplate entity) {
		return null;
	}

	@Override
	public String idAsStr(AccountTransactionTemplate entity) {
		if (entity == null) return null;
		return entity.getId() + "";
	}
}