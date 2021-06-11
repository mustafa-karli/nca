package com.nauticana.sales.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.sales.model.SalesOrderItemAttr;
import com.nauticana.sales.model.SalesOrderItemAttrId;

public interface SalesOrderItemAttrRepository extends JpaRepository<SalesOrderItemAttr, SalesOrderItemAttrId>{}
