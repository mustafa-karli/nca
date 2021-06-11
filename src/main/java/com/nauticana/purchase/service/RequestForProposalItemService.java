package com.nauticana.purchase.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.purchase.model.RequestForProposalItem;
import com.nauticana.purchase.model.RequestForProposalItemId;

@Service
public class RequestForProposalItemService extends AbstractService<RequestForProposalItem, RequestForProposalItemId> {

	@Autowired
	RequestForProposalService parentService;

	@Override
	@Transactional
	public RequestForProposalItem newEntityWithParentId(String parentKey) {
		RequestForProposalItem entity = new RequestForProposalItem();
		entity.setId(new RequestForProposalItemId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setRfpId(parentId);
		entity.setRequestForProposal(parentService.findById(parentId));
		return entity;
	}

	@Override
	public RequestForProposalItemId strToId(String id) {
		return new RequestForProposalItemId(id);
	}

	@Override
	public RequestForProposalItem newEntityWithId(String strId) {
		RequestForProposalItem entity = new RequestForProposalItem();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(RequestForProposalItem entity) {
		if (entity == null) return null;
		return "requestForProposal/show?id=" + entity.getId().getRfpId();
	}

	@Override
	public String idAsStr(RequestForProposalItem entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

