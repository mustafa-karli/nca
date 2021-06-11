package com.nauticana.shipment.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.shipment.model.Shipment;

@Service
public class ShipmentService extends AbstractService<Shipment, Integer> {

	@Override
	@Transactional
	public Shipment newEntityWithParentId(String parentKey) {
		Shipment entity = new Shipment();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(Shipment entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(Shipment entity) {
		return entity.getOwnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public Shipment newEntityWithId(String strId) {
		Shipment entity = new Shipment();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(Shipment entity) {
		return null;
	}

	@Override
	public String idAsStr(Shipment entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

}

