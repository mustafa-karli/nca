package com.nauticana.purchase.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.purchase.model.ProposalToRfpDialog;
import com.nauticana.purchase.model.ProposalToRfpDialogId;

@Service
public class ProposalToRfpDialogService extends AbstractService<ProposalToRfpDialog, ProposalToRfpDialogId> {

	@Autowired
	ProposalToRfpService parentService;

	@Override
	@Transactional
	public ProposalToRfpDialog newEntityWithParentId(String parentKey) {
		ProposalToRfpDialog entity = new ProposalToRfpDialog();
		entity.setId(new ProposalToRfpDialogId(parentKey));
		int parentId = parentService.strToId(parentKey);
		entity.setProposalToRfp(parentService.findById(parentId));
		return entity;
	}

	@Override
	public ProposalToRfpDialogId strToId(String id) {
		return new ProposalToRfpDialogId(id);
	}

	@Override
	public ProposalToRfpDialog newEntityWithId(String strId) {
		ProposalToRfpDialog entity = new ProposalToRfpDialog();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProposalToRfpDialog entity) {
		if (entity == null) return null;
		return "proposalToRfp/show?id=" + entity.getId().getProposalId();
	}

	@Override
	public String idAsStr(ProposalToRfpDialog entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

