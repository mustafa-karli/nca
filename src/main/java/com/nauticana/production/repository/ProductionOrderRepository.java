package com.nauticana.production.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.production.model.ProductionOrder;

public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Integer>{}
