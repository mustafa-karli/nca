package com.nauticana.maintenance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.maintenance.model.FinalEquipmentPart;
import com.nauticana.maintenance.model.FinalEquipmentPartId;

public interface FinalEquipmentPartRepository extends JpaRepository<FinalEquipmentPart, FinalEquipmentPartId>{}
