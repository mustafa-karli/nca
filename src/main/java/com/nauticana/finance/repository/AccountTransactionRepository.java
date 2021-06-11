package com.nauticana.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.finance.model.AccountTransaction;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {}