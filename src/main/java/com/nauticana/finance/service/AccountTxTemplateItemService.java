package com.nauticana.finance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.finance.model.AccountTxTemplateItem;
import com.nauticana.finance.model.AccountTxTemplateItemId;

@Service
public class AccountTxTemplateItemService extends AbstractService<AccountTxTemplateItem, AccountTxTemplateItemId> {

	@Autowired
	AccountTransactionTemplateService parentService;

	@Override
	@Transactional
	public AccountTxTemplateItem newEntityWithParentId(String parentKey) {
		AccountTxTemplateItem entity = new AccountTxTemplateItem();
		entity.setId(new AccountTxTemplateItemId());
		int parentId = Integer.parseInt(parentKey);
		entity.getId().setTemplateId(parentId);
		entity.setAccountTransactionTemplate(parentService.findById(parentId));
		return entity;
	}

	@Override
	public AccountTxTemplateItemId strToId(String id) {
		return new AccountTxTemplateItemId(id);
	}

	@Override
	public AccountTxTemplateItem newEntityWithId(String strId) {
		AccountTxTemplateItem entity = new AccountTxTemplateItem();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(AccountTxTemplateItem entity) {
		if (entity == null) return null;
		return "accountTransactionTemplate/show?id=" + entity.getAccountTransactionTemplate().getId();
	}

	@Override
	public String idAsStr(AccountTxTemplateItem entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}

