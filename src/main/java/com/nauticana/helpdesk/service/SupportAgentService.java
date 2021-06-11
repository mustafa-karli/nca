package com.nauticana.helpdesk.service;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.helpdesk.model.SupportAgent;
import com.nauticana.helpdesk.model.SupportAgentId;

@Service
public class SupportAgentService extends AbstractService<SupportAgent, SupportAgentId> {

	@Autowired
	SupportGroupService parentService;

	@Override
	@Transactional
	public SupportAgent newEntityWithParentId(String parentKey) {
		SupportAgent entity = new SupportAgent();
		entity.setId(new SupportAgentId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setSupportGroupId(parentId);
		entity.setSupportGroup(parentService.findById(parentId));
		return entity;
	}

	@Override
	public SupportAgentId strToId(String id) {
		return new SupportAgentId(id);
	}

	@Override
	public SupportAgent newEntityWithId(String strId) {
		SupportAgent entity = new SupportAgent();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(SupportAgent entity) {
		if (entity == null) return null;
		return "supportGroup/show?id=" + entity.getId().getSupportGroupId();
	}

	@Override
	public String idAsStr(SupportAgent entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}

