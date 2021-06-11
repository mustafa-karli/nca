package com.nauticana.finance.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.finance.model.Bank;

@Service
public class BankService extends AbstractService<Bank, String> {

	@Override
	@Transactional
	public Bank newEntityWithParentId(String parentKey) {
		Bank entity = new Bank();
		return entity;
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public Bank newEntityWithId(String strId) {
		Bank entity = new Bank();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<Bank> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Bank x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(Bank entity) {
		return null;
	}

	@Override
	public String idAsStr(Bank entity) {
		if (entity == null) return null;
		return entity.getId();
	}
}

