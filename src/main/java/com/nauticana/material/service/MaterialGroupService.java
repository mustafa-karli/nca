package com.nauticana.material.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.material.model.MaterialGroup;

@Service
public class MaterialGroupService extends AbstractService<MaterialGroup, Integer> {

	@Autowired
	MaterialGroupService parentService;

	@Override
	@Transactional
	public MaterialGroup newEntityWithParentId(String parentKey) {
		MaterialGroup entity = new MaterialGroup();
		if (!Utils.emptyStr(parentKey)) {
			MaterialGroup parent = parentService.findById(parentService.strToId(parentKey));
			entity.setMaterialGroup(parent);
			entity.setPurpose(parent.getPurpose());
			entity.setOwnerId(parent.getOwnerId());
		}
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(MaterialGroup entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(MaterialGroup entity) {
		return entity.getOwnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public MaterialGroup newEntityWithId(String strId) {
		MaterialGroup entity = new MaterialGroup();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<MaterialGroup> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(MaterialGroup x : list) {
			i++;
			items[i][0] = x.getId() + "";
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(MaterialGroup entity) {
		if (entity == null) return null;
		if (entity.getMaterialGroup() == null) return null;
		return MaterialGroup.ROOTMAPPING + "/show?id=" + entity.getMaterialGroup().getId();
	}

	@Override
	public String idAsStr(MaterialGroup entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}
}

