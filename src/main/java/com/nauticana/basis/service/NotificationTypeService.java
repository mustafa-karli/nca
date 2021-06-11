package com.nauticana.basis.service;

import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.NotificationType;

@Service
public class NotificationTypeService extends AbstractService<NotificationType,Integer> {

	@Override
	public NotificationType newEntityWithParentId(String parentKey) {
		NotificationType entity = new NotificationType();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};

	@Override
	public void setClient(NotificationType entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(NotificationType entity) {
		return entity.getOwnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public NotificationType newEntityWithId(String strId) {
		NotificationType entity = new NotificationType();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(NotificationType entity) {
		return null;
	}

	@Override
	public String idAsStr(NotificationType entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

}