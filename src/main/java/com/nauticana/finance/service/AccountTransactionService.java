package com.nauticana.finance.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.finance.model.AccountTransaction;

@Service
public class AccountTransactionService extends AbstractService<AccountTransaction, Long> {

	@Override
	@Transactional
	public AccountTransaction newEntityWithParentId(String parentKey) {
		AccountTransaction entity = new AccountTransaction();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};

	@Override
	public void setClient(AccountTransaction entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(AccountTransaction entity) {
		return entity.getOwnerId();
	};

	@Override
	public Long strToId(String id) {
		return Long.parseLong(id);
	}

	@Override
	public AccountTransaction newEntityWithId(String strId) {
		AccountTransaction entity = new AccountTransaction();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(AccountTransaction entity) {
		return null;
	}

	@Override
	public String idAsStr(AccountTransaction entity) {
		if (entity == null) return null;
		return entity.getId() + "";
	}
}