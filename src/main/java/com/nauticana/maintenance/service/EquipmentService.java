package com.nauticana.maintenance.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.maintenance.model.Equipment;
import com.nauticana.maintenance.model.EquipmentId;
import com.nauticana.material.service.MaterialService;

@Service
public class EquipmentService extends AbstractService<Equipment, EquipmentId> {

	@Autowired
	MaterialService parentService;

	@Override
	@Transactional
	public Equipment newEntityWithParentId(String parentKey) {
		Equipment entity = new Equipment();
		entity.setId(new EquipmentId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setMaterialId(parentId);
		entity.setMaterial(parentService.findById(parentId));
		return entity;
	}

	@Override
	public EquipmentId strToId(String id) {
		return new EquipmentId(id);
	}

	@Override
	public Equipment newEntityWithId(String strId) {
		Equipment entity = new Equipment();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<Equipment> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Equipment x : list) {
			i++;
			items[i][0] = x.getId().toString();
			items[i][1] = x.getMaterial().getCaption() + " " + x.getId().getSerialNo();
		}
		return items;
	}

	@Override
	public String parentLink(Equipment entity) {
		if (entity == null) return null;
		return "material/show?id=" + entity.getId().getMaterialId();
	}

	@Override
	public String idAsStr(Equipment entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}

