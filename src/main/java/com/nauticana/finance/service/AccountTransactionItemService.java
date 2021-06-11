package com.nauticana.finance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.finance.model.AccountTransactionItem;
import com.nauticana.finance.model.AccountTransactionItemId;

@Service
public class AccountTransactionItemService extends AbstractService<AccountTransactionItem, AccountTransactionItemId> {

	@Autowired
	AccountTransactionService parentService;

	@Override
	@Transactional
	public AccountTransactionItem newEntityWithParentId(String parentKey) {
		AccountTransactionItem entity = new AccountTransactionItem();
		entity.setId(new AccountTransactionItemId());
		long parentId = Long.parseLong(parentKey);
		entity.getId().setTransactionId(parentId);
		entity.setAccountTransaction(parentService.findById(parentId));
		return entity;
	}

	@Override
	public AccountTransactionItemId strToId(String id) {
		return new AccountTransactionItemId(id);
	}

	@Override
	public AccountTransactionItem newEntityWithId(String strId) {
		AccountTransactionItem entity = new AccountTransactionItem();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(AccountTransactionItem entity) {
		if (entity == null) return null;
		return "accountTransaction/show?id=" + entity.getId().getTransactionId();
	}

	@Override
	public String idAsStr(AccountTransactionItem entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}

