package com.nauticana.basis.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.basis.model.TreeData;

@Service
public class TreeDataService extends AbstractService<TreeData, Integer> {

	@Autowired
	TreeDataService parentService;

	@Override
	@Transactional
	public TreeData newEntityWithParentId(String parentKey) {
		TreeData entity = new TreeData();
		if (!Utils.emptyStr(parentKey)) {
			TreeData parent = parentService.findById(parentService.strToId(parentKey));
			entity.setTreeData(parent);
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
	public void setClient(TreeData entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(TreeData entity) {
		return entity.getOwnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public TreeData newEntityWithId(String strId) {
		TreeData entity = new TreeData();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<TreeData> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(TreeData x : list) {
			i++;
			items[i][0] = x.getId() + "";
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(TreeData entity) {
		if (entity == null) return null;
		if (entity.getTreeData() == null) return null;
		return TreeData.ROOTMAPPING + "/show?id=" + entity.getTreeData().getId();
	}

	@Override
	public String idAsStr(TreeData entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}
}

