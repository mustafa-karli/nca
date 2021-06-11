package com.nauticana.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.finance.model.AccountBalance;
import com.nauticana.finance.model.AccountBalanceId;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, AccountBalanceId> {}