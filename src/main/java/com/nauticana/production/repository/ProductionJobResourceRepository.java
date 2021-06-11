package com.nauticana.production.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.production.model.ProductionJobResource;
import com.nauticana.production.model.ProductionJobResourceId;

public interface ProductionJobResourceRepository extends JpaRepository<ProductionJobResource, ProductionJobResourceId>{}
