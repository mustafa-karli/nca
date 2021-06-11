package com.nauticana.material.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.MaterialReservation;

@Service
public class MaterialReservationService extends AbstractService<MaterialReservation, Integer> {

	@Autowired
	MaterialService parentService;

	@Override
	@Transactional
	public MaterialReservation newEntityWithParentId(String parentKey) {
		MaterialReservation entity = new MaterialReservation();
		entity.setMaterial(parentService.findById(parentService.strToId(parentKey)));
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public MaterialReservation newEntityWithId(String strId) {
		MaterialReservation entity = new MaterialReservation();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(MaterialReservation entity) {
		if (entity == null) return null;
		if (entity.getMaterial() == null) return null;
		return "material/show?id=" + entity.getMaterial().getId();
	}

	@Override
	public String idAsStr(MaterialReservation entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}
}

