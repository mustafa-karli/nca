package com.nauticana.shipment.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.shipment.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{}
