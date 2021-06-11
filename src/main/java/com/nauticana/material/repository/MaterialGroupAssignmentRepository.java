package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.MaterialGroupAssignment;
import com.nauticana.material.model.MaterialGroupAssignmentId;

public interface MaterialGroupAssignmentRepository extends JpaRepository<MaterialGroupAssignment, MaterialGroupAssignmentId>{}
