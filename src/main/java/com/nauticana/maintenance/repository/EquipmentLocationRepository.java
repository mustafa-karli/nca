package com.nauticana.maintenance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.maintenance.model.EquipmentLocation;
import com.nauticana.maintenance.model.EquipmentLocationId;

public interface EquipmentLocationRepository extends JpaRepository<EquipmentLocation, EquipmentLocationId>{}
