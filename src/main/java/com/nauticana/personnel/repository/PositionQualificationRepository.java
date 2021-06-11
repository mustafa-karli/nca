package com.nauticana.personnel.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.personnel.model.PositionQualification;
import com.nauticana.personnel.model.PositionQualificationId;

public interface PositionQualificationRepository extends JpaRepository<PositionQualification, PositionQualificationId>{}
