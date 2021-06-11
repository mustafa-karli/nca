package com.nauticana.finance.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.finance.model.BankAccount;

@Service
public class BankAccountService extends AbstractService<BankAccount, String> {

	@Override
	@Transactional
	public BankAccount newEntityWithParentId(String parentKey) {
		BankAccount entity = new BankAccount();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};

	@Override
	public void setClient(BankAccount entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(BankAccount entity) {
		return entity.getOwnerId();
	};

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public BankAccount newEntityWithId(String strId) {
		BankAccount entity = new BankAccount();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<BankAccount> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(BankAccount x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCurrency() + " " + x.getId();
		}
		return items;
	}

	@Override
	public String parentLink(BankAccount entity) {
		return null;
	}

	@Override
	public String idAsStr(BankAccount entity) {
		if (entity == null) return null;
		return entity.getId();
	}
}

