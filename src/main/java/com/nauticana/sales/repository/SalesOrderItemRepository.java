package com.nauticana.sales.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.sales.model.SalesOrderItem;
import com.nauticana.sales.model.SalesOrderItemId;

public interface SalesOrderItemRepository extends JpaRepository<SalesOrderItem, SalesOrderItemId>{}
