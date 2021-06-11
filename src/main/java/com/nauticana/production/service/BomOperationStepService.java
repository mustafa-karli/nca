package com.nauticana.production.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.production.model.BomOperation;
import com.nauticana.production.model.BomOperationStep;
import com.nauticana.production.model.BomOperationStepId;

@Service
public class BomOperationStepService extends AbstractService<BomOperationStep, BomOperationStepId> {

	@Autowired
	BomOperationService parentService;

	@Override
	@Transactional
	public BomOperationStep newEntityWithParentId(String parentKey) {
		BomOperationStep entity = new BomOperationStep();
		entity.setId(new BomOperationStepId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setBomOperationId(parentId);
		entity.setBomOperation(parentService.findById(parentId));
		return entity;
	}

	@Override
	public BomOperationStepId strToId(String id) {
		return new BomOperationStepId(id);
	}

	@Override
	public BomOperationStep newEntityWithId(String strId) {
		BomOperationStep entity = new BomOperationStep();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(BomOperationStep entity) {
		if (entity == null) return null;
		return BomOperation.ROOTMAPPING + "/show?id=" + entity.getId().getBomOperationId();
	}

	@Override
	public String idAsStr(BomOperationStep entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

