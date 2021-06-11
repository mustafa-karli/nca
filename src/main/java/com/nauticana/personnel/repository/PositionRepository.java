package com.nauticana.personnel.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.personnel.model.Position;
import com.nauticana.personnel.model.PositionId;

public interface PositionRepository extends JpaRepository<Position, PositionId>{}
