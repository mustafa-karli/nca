package com.nauticana.purchase.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.purchase.model.ProposalToRfpItem;
import com.nauticana.purchase.model.ProposalToRfpItemId;

@Service
public class ProposalToRfpItemService extends AbstractService<ProposalToRfpItem, ProposalToRfpItemId> {

	@Autowired
	ProposalToRfpService parentService;

	@Override
	@Transactional
	public ProposalToRfpItem newEntityWithParentId(String parentKey) {
		ProposalToRfpItem entity = new ProposalToRfpItem();
		entity.setId(new ProposalToRfpItemId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setProposalId(parentId);
		entity.setProposalToRfp(parentService.findById(parentId));
		return entity;
	}

	@Override
	public ProposalToRfpItemId strToId(String id) {
		return new ProposalToRfpItemId(id);
	}

	@Override
	public ProposalToRfpItem newEntityWithId(String strId) {
		ProposalToRfpItem entity = new ProposalToRfpItem();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProposalToRfpItem entity) {
		if (entity == null) return null;
		return "proposalToRfp/show?id=" + entity.getId().getProposalId();
	}

	@Override
	public String idAsStr(ProposalToRfpItem entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

