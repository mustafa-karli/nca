package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.MaterialMovement;

public interface MaterialMovementRepository extends JpaRepository<MaterialMovement, Integer>{}
