package com.nauticana.request.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.request.model.MaterialRequestItemAttr;
import com.nauticana.request.model.MaterialRequestItemAttrId;
import com.nauticana.request.model.MaterialRequestItemId;

@Service
public class MaterialRequestItemAttrService extends AbstractService<MaterialRequestItemAttr, MaterialRequestItemAttrId> {

	@Autowired
	MaterialRequestItemService parentService;

	@Override
	@Transactional
	public MaterialRequestItemAttr newEntityWithParentId(String parentKey) {
		MaterialRequestItemAttr entity = new MaterialRequestItemAttr();
		entity.setId(new MaterialRequestItemAttrId());
		MaterialRequestItemId parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialRequestId(parentId.getMaterialRequestId());
		entity.getId().setLine(parentId.getLine());
		entity.setMaterialRequestItem(parentService.findById(parentId));
		return entity;
	}

	@Override
	public MaterialRequestItemAttrId strToId(String id) {
		return new MaterialRequestItemAttrId(id);
	}

	@Override
	public MaterialRequestItemAttr newEntityWithId(String strId) {
		MaterialRequestItemAttr entity = new MaterialRequestItemAttr();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaterialRequestItemAttr entity) {
		if (entity == null) return null;
		if (entity.getMaterialRequestItem() == null) return null;
		return "materialRequestItem/show?id=" + entity.getMaterialRequestItem().getId().toString();
	}

	@Override
	public String idAsStr(MaterialRequestItemAttr entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

