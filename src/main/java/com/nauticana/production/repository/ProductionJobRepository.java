package com.nauticana.production.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.production.model.ProductionJob;

public interface ProductionJobRepository extends JpaRepository<ProductionJob, Integer>{}
