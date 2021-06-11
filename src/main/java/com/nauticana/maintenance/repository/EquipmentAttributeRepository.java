package com.nauticana.maintenance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.maintenance.model.EquipmentAttribute;
import com.nauticana.maintenance.model.EquipmentAttributeId;

public interface EquipmentAttributeRepository extends JpaRepository<EquipmentAttribute, EquipmentAttributeId>{}
