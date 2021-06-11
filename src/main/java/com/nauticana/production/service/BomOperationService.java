package com.nauticana.production.service;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.service.MaterialService;
import com.nauticana.production.model.BomOperation;

@Service
public class BomOperationService extends AbstractService<BomOperation, Integer> {

	@Autowired
	MaterialService parentService;

	@Override
	@Transactional
	public BomOperation newEntityWithParentId(String parentKey) {
		BomOperation entity = new BomOperation();
		entity.setMaterial(parentService.findById(Integer.parseInt(parentKey)));
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public BomOperation newEntityWithId(String strId) {
		BomOperation entity = new BomOperation();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(BomOperation entity) {
		if (entity == null) return null;
		if (entity.getMaterial() == null) return null;
		return "material/show?id=" + entity.getMaterial().getId();
	}

	@Override
	public String idAsStr(BomOperation entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

}

