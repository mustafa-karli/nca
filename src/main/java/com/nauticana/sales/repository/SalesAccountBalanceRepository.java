package com.nauticana.sales.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.sales.model.SalesAccountBalance;

public interface SalesAccountBalanceRepository extends JpaRepository<SalesAccountBalance, Integer>{}
