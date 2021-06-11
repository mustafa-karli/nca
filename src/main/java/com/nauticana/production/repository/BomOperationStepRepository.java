package com.nauticana.production.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.production.model.BomOperationStep;
import com.nauticana.production.model.BomOperationStepId;

public interface BomOperationStepRepository extends JpaRepository<BomOperationStep, BomOperationStepId>{}
