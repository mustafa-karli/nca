package com.nauticana.personnel.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.personnel.model.Organization;
import com.nauticana.personnel.model.Position;
import com.nauticana.personnel.model.PositionId;

@Service
public class PositionService extends AbstractService<Position, PositionId> {

	@Autowired
	OrganizationService parentService;

	@Override
	@Transactional
	public Position newEntityWithParentId(String parentKey) {
		Position entity = new Position();
		entity.setId(new PositionId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setOrganizationId(parentId);
		Organization parent = parentService.findById(parentId);
		entity.setOrganization(parent);
		entity.getId().setOwnerId(parent.getPartnerAddress().getId().getBusinessPartnerId());
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(Position entity, int client) {
		entity.getId().setOwnerId(client);
	};

	@Override
	public int getClient(Position entity) {
		return entity.getId().getOwnerId();
	};

	@Override
	public PositionId strToId(String id) {
		return new PositionId(id);
	}

	@Override
	public Position newEntityWithId(String strId) {
		Position entity = new Position();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(Position entity) {
		if (entity == null) return null;
		return Organization.ROOTMAPPING + "/show?id=" + entity.getId().getOrganizationId();
	}

	@Override
	public String idAsStr(Position entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

