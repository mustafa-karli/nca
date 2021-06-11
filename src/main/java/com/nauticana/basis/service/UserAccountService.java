package com.nauticana.basis.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.UserAccount;

@Service
public class UserAccountService extends AbstractService<UserAccount, String> {

	@Override
	public UserAccount newEntityWithParentId(String parentKey) {
		UserAccount entity = new UserAccount();
		entity.setStatus("I");
		return entity;
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public UserAccount newEntityWithId(String strId) {
		UserAccount entity = new UserAccount();
		entity.setId(strToId(strId));
		entity.setStatus("I");
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<UserAccount> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(UserAccount x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getFirstName() + " " + x.getLastName();
		}
		return items;
	}

	@Override
	public String parentLink(UserAccount entity) {
		return null;
	}

	@Override
	public String idAsStr(UserAccount entity) {
		if (entity == null) return null;
		return entity.getId();
	}

	@SuppressWarnings("unchecked")
	public List<UserAccount> findByEmail(String email) {
		String findAllSql = "SELECT T.* FROM USER_ACCOUNT T WHERE EMAIL_ADDRESS='" + email + "'";
		Query query = entityManager.createNativeQuery(findAllSql, modelClass());
		return query.getResultList();
	}
}

