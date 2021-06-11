package com.nauticana.production.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.production.model.ProductionLineReason;
import com.nauticana.production.model.ProductionLineReasonId;

public interface ProductionLineReasonRepository extends JpaRepository<ProductionLineReason, ProductionLineReasonId>{}
