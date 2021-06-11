package com.nauticana.production.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.production.model.BomOperation;

public interface BomOperationRepository extends JpaRepository<BomOperation, Integer>{}
