package com.nauticana.personnel.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.personnel.model.PositionAssignment;
import com.nauticana.personnel.model.PositionAssignmentId;

public interface PositionAssignmentRepository extends JpaRepository<PositionAssignment, PositionAssignmentId>{}
