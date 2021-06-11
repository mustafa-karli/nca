package com.nauticana.material.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.MaterialGroup;
import com.nauticana.material.model.MaterialGroupAssignment;
import com.nauticana.material.model.MaterialGroupAssignmentId;

@Service
public class MaterialGroupAssignmentService extends AbstractService<MaterialGroupAssignment, MaterialGroupAssignmentId> {

	@Autowired
	MaterialGroupService parentService;

	@Override
	@Transactional
	public MaterialGroupAssignment newEntityWithParentId(String parentKey) {
		MaterialGroupAssignment entity = new MaterialGroupAssignment();
		entity.setId(new MaterialGroupAssignmentId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialGroupId(parentId);
		entity.setMaterialGroup(parentService.findById(parentId));
		return entity;
	}

	@Override
	public MaterialGroupAssignmentId strToId(String id) {
		return new MaterialGroupAssignmentId(id);
	}

	@Override
	public MaterialGroupAssignment newEntityWithId(String strId) {
		MaterialGroupAssignment entity = new MaterialGroupAssignment();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaterialGroupAssignment entity) {
		if (entity == null) return null;
		return MaterialGroup.ROOTMAPPING + "/show?id=" + entity.getId().getMaterialGroupId();
	}

	@Override
	public String idAsStr(MaterialGroupAssignment entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

