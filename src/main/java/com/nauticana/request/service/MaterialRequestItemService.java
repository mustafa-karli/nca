package com.nauticana.request.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.request.model.MaterialRequestItem;
import com.nauticana.request.model.MaterialRequestItemId;

@Service
public class MaterialRequestItemService extends AbstractService<MaterialRequestItem, MaterialRequestItemId> {

	@Autowired
	MaterialRequestService parentService;

	@Override
	@Transactional
	public MaterialRequestItem newEntityWithParentId(String parentKey) {
		MaterialRequestItem entity = new MaterialRequestItem();
		entity.setId(new MaterialRequestItemId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialRequestId(parentId);
		entity.setMaterialRequest(parentService.findById(parentId));
		return entity;
	}

	@Override
	public MaterialRequestItemId strToId(String id) {
		return new MaterialRequestItemId(id);
	}

	@Override
	public MaterialRequestItem newEntityWithId(String strId) {
		MaterialRequestItem entity = new MaterialRequestItem();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaterialRequestItem entity) {
		if (entity == null) return null;
		return "materialRequest/show?id=" + entity.getId().getMaterialRequestId();
	}

	@Override
	public String idAsStr(MaterialRequestItem entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

