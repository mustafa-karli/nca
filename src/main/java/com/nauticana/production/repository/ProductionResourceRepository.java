package com.nauticana.production.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.production.model.ProductionResource;
import com.nauticana.production.model.ProductionResourceId;

public interface ProductionResourceRepository extends JpaRepository<ProductionResource, ProductionResourceId>{}
