package com.nauticana.basis.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.UserNotification;
import com.nauticana.basis.repository.UserAccountRepository;
import com.nauticana.basis.utils.Utils;

@Service
public class UserNotificationService extends AbstractService<UserNotification, Long> {

	@Autowired
	UserAccountRepository parentRep;

	@Override
	public UserNotification newEntityWithParentId(String parentKey) {
		UserNotification entity = new UserNotification();
		if (!Utils.emptyStr(parentKey)) {
			entity.setUserAccount(parentRep.findById(parentKey).get());
		}
		return entity;
	}

	@Override
	public Long strToId(String id) {
		return Long.parseLong(id);
	}

	@Override
	public UserNotification newEntityWithId(String strId) {
		UserNotification entity = new UserNotification();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(UserNotification entity) {
		if (entity == null) return null;
		if (entity.getUserAccount() == null) return null;
		return "userAccount/show?id=" + entity.getUserAccount().getId();
	}

	@Override
	public String idAsStr(UserNotification entity) {
		if (entity == null) return null;
		return entity.getId() + "";
	}
	
	@SuppressWarnings("unchecked")
	public List<UserNotification> activeUserNotifications(String username){
		String sql = "SELECT T.* FROM USER_NOTIFICATION T WHERE T.USERNAME = ? AND T.STATUS IN ('I','R')";
		Query query = entityManager.createNativeQuery(sql, modelClass());
		query.setParameter(1, username);
		return query.getResultList();
	}
}
