package com.nauticana.shipment.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.shipment.model.ShipmentLine;
import com.nauticana.shipment.model.ShipmentLineId;

public interface ShipmentLineRepository extends JpaRepository<ShipmentLine, ShipmentLineId>{}
