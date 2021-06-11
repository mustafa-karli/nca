package com.nauticana.production.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.production.model.BomOperationStep;
import com.nauticana.production.model.BomOperationStepId;
import com.nauticana.production.model.BomOperationStepResource;
import com.nauticana.production.model.BomOperationStepResourceId;

@Service
public class BomOperationStepResourceService extends AbstractService<BomOperationStepResource, BomOperationStepResourceId> {

	@Autowired
	BomOperationStepService parentService;

	@Override
	@Transactional
	public BomOperationStepResource newEntityWithParentId(String parentKey) {
		BomOperationStepResource entity = new BomOperationStepResource();
		entity.setId(new BomOperationStepResourceId());
		BomOperationStepId parentId = parentService.strToId(parentKey);
		entity.getId().setBomOperationId(parentId.getBomOperationId());
		entity.getId().setStep(parentId.getStep());
		entity.setBomOperationStep(parentService.findById(parentId));
		return entity;
	}

	@Override
	public BomOperationStepResourceId strToId(String id) {
		return new BomOperationStepResourceId(id);
	}

	@Override
	public BomOperationStepResource newEntityWithId(String strId) {
		BomOperationStepResource entity = new BomOperationStepResource();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(BomOperationStepResource entity) {
		if (entity == null) return null;
		if (entity.getBomOperationStep() == null) return null;
		return BomOperationStep.ROOTMAPPING + "/show?id=" + entity.getBomOperationStep().getId().toString();
	}

	@Override
	public String idAsStr(BomOperationStepResource entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

