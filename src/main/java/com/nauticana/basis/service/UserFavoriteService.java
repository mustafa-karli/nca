package com.nauticana.basis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.UserFavorite;
import com.nauticana.basis.model.UserFavoriteId;
import com.nauticana.basis.repository.UserAccountRepository;
import com.nauticana.basis.utils.Utils;

@Service
public class UserFavoriteService extends AbstractService<UserFavorite, UserFavoriteId> {

	@Autowired
	UserAccountRepository parentRep;

	@Override
	public UserFavorite newEntityWithParentId(String parentKey) {
		UserFavorite entity = new UserFavorite();
		if (!Utils.emptyStr(parentKey)) {
			UserFavoriteId id = new UserFavoriteId();
			id.setUsername(parentKey);
			entity.setId(id);
			entity.setUserAccount(parentRep.findById(parentKey).get());
		}
		return entity;
	}

	@Override
	public UserFavoriteId strToId(String id) {
		return new UserFavoriteId(id);
	}

	@Override
	public UserFavorite newEntityWithId(String strId) {
		UserFavorite entity = new UserFavorite();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(UserFavorite entity) {
		if (entity == null) return null;
		return "userAccount/show?id=" + entity.getId().getUsername();
	}

	@Override
	public String idAsStr(UserFavorite entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}
