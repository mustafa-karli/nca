package com.nauticana.basis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.model.NotificationRecipient;
import com.nauticana.basis.model.NotificationRecipientId;
import com.nauticana.basis.repository.NotificationTypeRepository;
import com.nauticana.basis.utils.Utils;

@Service
public class NotificationRecipientService extends AbstractService<NotificationRecipient,NotificationRecipientId> {

	@Autowired
	NotificationTypeRepository parentRep;

	@Override
	public NotificationRecipient newEntityWithParentId(String parentKey) {
		NotificationRecipient entity = new NotificationRecipient();
		if (!Utils.emptyStr(parentKey)) {
			NotificationRecipientId id = new NotificationRecipientId();
			int parentId = Integer.parseInt(parentKey);
			id.setNotificationTypeId(parentId);
			entity.setNotificationType(parentRep.findById(parentId).get());
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public NotificationRecipientId strToId(String id) {
		return new NotificationRecipientId(id);
	}

	@Override
	public NotificationRecipient newEntityWithId(String strId) {
		NotificationRecipient entity = new NotificationRecipient();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(NotificationRecipient entity) {
		if (entity == null) return null;
		return "notificationType/show?id=" + entity.getId().getNotificationTypeId();
	}

	@Override
	public String idAsStr(NotificationRecipient entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}