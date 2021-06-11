package com.nauticana.basis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.AuthorityGroup;
import com.nauticana.basis.model.ObjectAuthorization;
import com.nauticana.basis.model.ObjectAuthorizationId;
import com.nauticana.basis.repository.AuthorityGroupRepository;
import com.nauticana.basis.utils.Utils;


@Service
public class ObjectAuthorizationService extends AbstractService<ObjectAuthorization,ObjectAuthorizationId> {

	@Autowired
	AuthorityGroupRepository parentRep;

	@Override
	public ObjectAuthorization newEntityWithParentId(String parentKey) {
		ObjectAuthorization entity = new ObjectAuthorization();
		if (!Utils.emptyStr(parentKey)) {
			ObjectAuthorizationId id = new ObjectAuthorizationId();
			id.setAuthorityGroup(parentKey);
			entity.setAuthorityGroup(parentRep.findById(parentKey).get());
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public ObjectAuthorizationId strToId(String id) {
		String[] s = id.split(",");
		return new ObjectAuthorizationId(s[0], s[1], s[2], s[3]);
	}

	@Override
	public ObjectAuthorization newEntityWithId(String strId) {
		ObjectAuthorization entity = new ObjectAuthorization();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ObjectAuthorization entity) {
		if (entity == null) return null;
		return AuthorityGroup.ROOTMAPPING + "/show?id=" + entity.getId().getAuthorityGroup();
	}

	@Override
	public String idAsStr(ObjectAuthorization entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}
