package com.nauticana.personnel.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.personnel.model.PositionType;
import com.nauticana.personnel.model.PositionTypeId;

@Service
public class PositionTypeService extends AbstractService<PositionType, PositionTypeId> {

	@Override
	@Transactional
	public PositionType newEntityWithParentId(String parentKey) {
		PositionType entity = new PositionType();
		entity.setId(new PositionTypeId());
//		entity.getId().setOwnerId(Integer.parseInt(parentKey));
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(PositionType entity, int client) {
		entity.getId().setOwnerId(client);
	};

	@Override
	public int getClient(PositionType entity) {
		return entity.getId().getOwnerId();
	};

	@Override
	public PositionTypeId strToId(String id) {
		return new PositionTypeId(id);
	}

	@Override
	public PositionType newEntityWithId(String strId) {
		PositionType entity = new PositionType();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<PositionType> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(PositionType x : list) {
			i++;
			items[i][0] = x.getId().getPosition();
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(PositionType entity) {
		return null;
	}

	@Override
	public String idAsStr(PositionType entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

