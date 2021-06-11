package com.nauticana.personnel.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.personnel.model.PositionType;
import com.nauticana.personnel.model.PositionTypeId;

public interface PositionTypeRepository extends JpaRepository<PositionType, PositionTypeId>{}
