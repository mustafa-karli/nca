package com.nauticana.sales.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.sales.model.SalesOrderTax;
import com.nauticana.sales.model.SalesOrderTaxId;

public interface SalesOrderTaxRepository extends JpaRepository<SalesOrderTax, SalesOrderTaxId>{}
