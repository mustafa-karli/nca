package com.nauticana.maintenance.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.maintenance.model.ServiceType;

@Service
public class ServiceTypeService extends AbstractService<ServiceType, Integer> {

	@Override
	@Transactional
	public ServiceType newEntityWithParentId(String parentKey) {
		ServiceType entity = new ServiceType();
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public ServiceType newEntityWithId(String strId) {
		ServiceType entity = new ServiceType();
		entity.setId(Integer.parseInt(strId));
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};

	@Override
	public void setClient(ServiceType entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(ServiceType entity) {
		return entity.getOwnerId();
	};

	@Override
	public String[][] findAllForLookup(int client) {
		List<ServiceType> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(ServiceType x : list) {
			i++;
			items[i][0] = x.getId() + "";
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(ServiceType entity) {
		return null;
	}

	@Override
	public String idAsStr(ServiceType entity) {
		if (entity == null) return null;
		return entity.getId() + "";
	}
}

