package com.nauticana.purchase.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.purchase.model.RfpPublishment;
import com.nauticana.purchase.model.RfpPublishmentId;

@Service
public class RfpPublishmentService extends AbstractService<RfpPublishment, RfpPublishmentId> {

	@Autowired
	RequestForProposalService parentService;

	@Override
	@Transactional
	public RfpPublishment newEntityWithParentId(String parentKey) {
		RfpPublishment entity = new RfpPublishment();
		entity.setId(new RfpPublishmentId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setRfpId(parentId);
		entity.setRequestForProposal(parentService.findById(parentId));
		return entity;
	}

	@Override
	public RfpPublishmentId strToId(String id) {
		return new RfpPublishmentId(id);
	}

	@Override
	public RfpPublishment newEntityWithId(String strId) {
		RfpPublishment entity = new RfpPublishment();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(RfpPublishment entity) {
		if (entity == null) return null;
		return "requestForProposal/show?id=" + entity.getId().getRfpId();
	}

	@Override
	public String idAsStr(RfpPublishment entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

