package com.nauticana.personnel.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.personnel.model.UserAccountOwner;
import com.nauticana.personnel.model.UserAccountOwnerId;

@Service
public class UserAccountOwnerService extends AbstractService<UserAccountOwner, UserAccountOwnerId> {

	@Autowired
	PersonService parentService;

	@Override
	@Transactional
	public UserAccountOwner newEntityWithParentId(String parentKey) {
		UserAccountOwner entity = new UserAccountOwner();
		entity.setId(new UserAccountOwnerId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setPersonId(parentId);
		entity.setPerson(parentService.findById(parentId));
		return entity;
	}

	@Override
	public UserAccountOwnerId strToId(String id) {
		return new UserAccountOwnerId(id);
	}

	@Override
	public UserAccountOwner newEntityWithId(String strId) {
		UserAccountOwner entity = new UserAccountOwner();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(UserAccountOwner entity) {
		if (entity == null) return null;
		return "person/show?id=" + entity.getId().getPersonId();
	}

	@Override
	public String idAsStr(UserAccountOwner entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

