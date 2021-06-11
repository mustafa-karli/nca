package com.nauticana.maintenance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.maintenance.model.Equipment;
import com.nauticana.maintenance.model.EquipmentId;

public interface EquipmentRepository extends JpaRepository<Equipment, EquipmentId>{}
