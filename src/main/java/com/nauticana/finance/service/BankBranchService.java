package com.nauticana.finance.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.finance.model.BankBranch;

@Service
public class BankBranchService extends AbstractService<BankBranch, String> {

	@Override
	@Transactional
	public BankBranch newEntityWithParentId(String parentKey) {
		BankBranch entity = new BankBranch();
		return entity;
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public BankBranch newEntityWithId(String strId) {
		BankBranch entity = new BankBranch();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<BankBranch> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(BankBranch x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getBank().getCaption() + " " + x.getAddress();
		}
		return items;
	}

	@Override
	public String parentLink(BankBranch entity) {
		return null;
	}

	@Override
	public String idAsStr(BankBranch entity) {
		if (entity == null) return null;
		return entity.getId();
	}
}

