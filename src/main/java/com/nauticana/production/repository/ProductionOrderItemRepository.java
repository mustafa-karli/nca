package com.nauticana.production.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.production.model.ProductionOrderItem;
import com.nauticana.production.model.ProductionOrderItemId;

public interface ProductionOrderItemRepository extends JpaRepository<ProductionOrderItem, ProductionOrderItemId>{}
