package com.nauticana.shipment.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.shipment.model.Shipment;
import com.nauticana.shipment.model.ShipmentLine;
import com.nauticana.shipment.model.ShipmentLineId;

@Service
public class ShipmentLineService extends AbstractService<ShipmentLine, ShipmentLineId> {

	@Autowired
	ShipmentService parentService;

	@Override
	@Transactional
	public ShipmentLine newEntityWithParentId(String parentKey) {
		ShipmentLine entity = new ShipmentLine();
		entity.setId(new ShipmentLineId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setShipmentId(parentId);
		entity.setShipment(parentService.findById(parentId));
		return entity;
	}

	@Override
	public ShipmentLineId strToId(String id) {
		return new ShipmentLineId(id);
	}

	@Override
	public ShipmentLine newEntityWithId(String strId) {
		ShipmentLine entity = new ShipmentLine();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ShipmentLine entity) {
		if (entity == null) return null;
		return Shipment.ROOTMAPPING + "/show?id=" + entity.getId().getShipmentId();
	}

	@Override
	public String idAsStr(ShipmentLine entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

