package com.nauticana.helpdesk.service;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.helpdesk.model.TicketEvent;
import com.nauticana.helpdesk.model.TicketEventId;

@Service
public class TicketEventService extends AbstractService<TicketEvent, TicketEventId> {

	@Autowired
	SupportTicketService parentService;

	@Override
	@Transactional
	public TicketEvent newEntityWithParentId(String parentKey) {
		TicketEvent entity = new TicketEvent();
		entity.setId(new TicketEventId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setTicketId(parentId);
		entity.setSupportTicket(parentService.findById(parentId));
		return entity;
	}

	@Override
	public TicketEventId strToId(String id) {
		return new TicketEventId(id);
	}

	@Override
	public TicketEvent newEntityWithId(String strId) {
		TicketEvent entity = new TicketEvent();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(TicketEvent entity) {
		if (entity == null) return null;
		return "supportTicket/show?id=" + entity.getId().getTicketId();
	}

	@Override
	public String idAsStr(TicketEvent entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}

