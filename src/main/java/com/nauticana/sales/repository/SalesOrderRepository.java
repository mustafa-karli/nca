package com.nauticana.sales.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.sales.model.SalesOrder;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Integer>{}
