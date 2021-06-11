package com.nauticana.basis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.UserAuthorization;
import com.nauticana.basis.model.UserAuthorizationId;
import com.nauticana.basis.repository.UserAccountRepository;
import com.nauticana.basis.utils.Utils;

@Service
public class UserAuthorizationService extends AbstractService<UserAuthorization,UserAuthorizationId> {

	@Autowired
	UserAccountRepository parentRep;

	@Override
	public UserAuthorization newEntityWithParentId(String parentKey) {
		UserAuthorization entity = new UserAuthorization();
		if (!Utils.emptyStr(parentKey)) {
			UserAuthorizationId id = new UserAuthorizationId();
			id.setUsername(parentKey);
			id.setBegda(Utils.onlyDate());
			entity.setId(id);
			entity.setEndda(Utils.maxDate());
			entity.setUserAccount(parentRep.findById(parentKey).get());
		}
		return entity;
	}

	@Override
	public UserAuthorizationId strToId(String id) {
		return new UserAuthorizationId(id);
	}

	@Override
	public UserAuthorization newEntityWithId(String strId) {
		UserAuthorization entity = new UserAuthorization();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(UserAuthorization entity) {
		if (entity == null) return null;
		return "userAccount/show?id=" + entity.getId().getUsername();
	}

	@Override
	public String idAsStr(UserAuthorization entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}

}
