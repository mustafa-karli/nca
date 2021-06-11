package com.nauticana.basis.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.AuthorityObject;
import com.nauticana.basis.model.AuthorityObjectAction;
import com.nauticana.basis.model.AuthorityObjectActionId;
import com.nauticana.basis.repository.AuthorityObjectRepository;
import com.nauticana.basis.utils.Utils;


@Service
public class AuthorityObjectActionService extends AbstractService<AuthorityObjectAction,AuthorityObjectActionId> {

	@Autowired
	AuthorityObjectRepository parentRep;
	
	@Override
	public AuthorityObjectAction newEntityWithParentId(String parentKey) {
		AuthorityObjectAction entity = new AuthorityObjectAction();
		if (!Utils.emptyStr(parentKey)) {
			AuthorityObjectActionId id = new AuthorityObjectActionId();
			id.setObjectType(parentKey);
			entity.setAuthorityObject(parentRep.findById(parentKey).get());
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public AuthorityObjectActionId strToId(String id) {
		String[] s = id.split(",");
		return new AuthorityObjectActionId(s[0], s[1]);
	}

	@Override
	public AuthorityObjectAction newEntityWithId(String strId) {
		AuthorityObjectAction entity = new AuthorityObjectAction();
		entity.setId(strToId(strId));
		return entity;
	}

	public Map<String, String> findAllStr(String authorityObject) {
		Map<String, String> list = new HashMap<String, String>();
		for (AuthorityObjectAction aoa : parentRep.getOne(authorityObject).getAuthorityObjectActions()) {
			list.put(aoa.getId().getAction(), aoa.getId().getAction());
		}
		return list;
	}

	@Override
	public String parentLink(AuthorityObjectAction entity) {
		if (entity == null) return null;
		return AuthorityObject.ROOTMAPPING + "/show?id=" + entity.getId().getObjectType();
	}

	@Override
	public String idAsStr(AuthorityObjectAction entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}
