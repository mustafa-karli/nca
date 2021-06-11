package com.nauticana.maintenance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.maintenance.model.EquipmentAssignment;
import com.nauticana.maintenance.model.EquipmentAssignmentId;

public interface EquipmentAssignmentRepository extends JpaRepository<EquipmentAssignment, EquipmentAssignmentId>{}
