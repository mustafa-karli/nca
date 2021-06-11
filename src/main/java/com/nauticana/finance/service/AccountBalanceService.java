package com.nauticana.finance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.finance.model.AccountBalance;
import com.nauticana.finance.model.AccountBalanceId;

@Service
public class AccountBalanceService extends AbstractService<AccountBalance, AccountBalanceId> {

	@Autowired
	AccountSchemaService parentService;

	@Override
	@Transactional
	public AccountBalance newEntityWithParentId(String parentKey) {
		AccountBalance entity = new AccountBalance();
		entity.setId(new AccountBalanceId());
		int parentId = Integer.parseInt(parentKey);
		entity.getId().setAccountSchemaId(parentId);
		entity.setAccountSchema(parentService.findById(parentId));
		return entity;
	}

	@Override
	public AccountBalanceId strToId(String id) {
		return new AccountBalanceId(id);
	}

	@Override
	public AccountBalance newEntityWithId(String strId) {
		AccountBalance entity = new AccountBalance();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(AccountBalance entity) {
		if (entity == null) return null;
		return "accountSchema/show?id=" + entity.getId().getAccountSchemaId();
	}

	@Override
	public String idAsStr(AccountBalance entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}