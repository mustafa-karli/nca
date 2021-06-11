package com.nauticana.helpdesk.service;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.helpdesk.model.SupportTicket;

@Service
public class SupportTicketService extends AbstractService<SupportTicket, Integer> {

	@Override
	@Transactional
	public SupportTicket newEntityWithParentId(String parentKey) {
		SupportTicket entity = new SupportTicket();
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public SupportTicket newEntityWithId(String strId) {
		SupportTicket entity = new SupportTicket();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(SupportTicket entity) {
		return null;
	}

	@Override
	public String idAsStr(SupportTicket entity) {
		if (entity == null) return null;
		return entity.getId() + "";
	}
}