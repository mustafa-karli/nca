package com.nauticana.material.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.material.model.MaterialAttributeGroup;

@Service
public class MaterialAttributeGroupService extends AbstractService<MaterialAttributeGroup, String> {

	@Override
	@Transactional
	public MaterialAttributeGroup newEntityWithParentId(String parentKey) {
		MaterialAttributeGroup entity = new MaterialAttributeGroup();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(MaterialAttributeGroup entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(MaterialAttributeGroup entity) {
		return entity.getOwnerId();
	};

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public MaterialAttributeGroup newEntityWithId(String strId) {
		MaterialAttributeGroup entity = new MaterialAttributeGroup();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<MaterialAttributeGroup> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(MaterialAttributeGroup x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(MaterialAttributeGroup entity) {
		return null;
	}

	@Override
	public String idAsStr(MaterialAttributeGroup entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

