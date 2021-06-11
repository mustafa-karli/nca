package com.nauticana.shipment.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.shipment.model.ViewShipmentLineStatus;
import com.nauticana.shipment.repository.ShipmentJbdcRepository;

@Service
public class ShipmentJdbcService {

	@Autowired
	private ShipmentJbdcRepository r;

    public List<ViewShipmentLineStatus> getShipmentLineStatus(int client, Date begda, Date endda, String status) {
		List<ViewShipmentLineStatus> l = r.getShipmentLineStatus(client, begda, endda, status);
		return l;
	}

}
