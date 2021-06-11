package com.nauticana.production.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.production.model.BomOperationStepResource;
import com.nauticana.production.model.BomOperationStepResourceId;

public interface BomOperationStepResourceRepository extends JpaRepository<BomOperationStepResource, BomOperationStepResourceId>{}
