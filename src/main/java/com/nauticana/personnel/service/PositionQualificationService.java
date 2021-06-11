package com.nauticana.personnel.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.personnel.model.Position;
import com.nauticana.personnel.model.PositionId;
import com.nauticana.personnel.model.PositionQualification;
import com.nauticana.personnel.model.PositionQualificationId;

@Service
public class PositionQualificationService extends AbstractService<PositionQualification, PositionQualificationId> {

	@Autowired
	PositionService parentService;

	@Override
	@Transactional
	public PositionQualification newEntityWithParentId(String parentKey) {
		PositionQualification entity = new PositionQualification();
		entity.setId(new PositionQualificationId());
		PositionId parentId = parentService.strToId(parentKey);
		entity.getId().setOrganizationId(parentId.getOrganizationId());
		entity.getId().setPosition(parentId.getPosition());
		entity.getId().setOwnerId(parentId.getOwnerId());
		entity.setPosition(parentService.findById(parentId));
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(PositionQualification entity, int client) {
		entity.getId().setOwnerId(client);
	};

	@Override
	public int getClient(PositionQualification entity) {
		return entity.getId().getOwnerId();
	};

	@Override
	public PositionQualificationId strToId(String id) {
		return new PositionQualificationId(id);
	}

	@Override
	public PositionQualification newEntityWithId(String strId) {
		PositionQualification entity = new PositionQualification();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(PositionQualification entity) {
		if (entity == null) return null;
		if (entity.getPosition() == null) return null;
		return Position.ROOTMAPPING + "/show?id=" + entity.getPosition().getId().toString();
	}

	@Override
	public String idAsStr(PositionQualification entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

