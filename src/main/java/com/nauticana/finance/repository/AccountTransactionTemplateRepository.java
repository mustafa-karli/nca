package com.nauticana.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.finance.model.AccountTransactionTemplate;

public interface AccountTransactionTemplateRepository extends JpaRepository<AccountTransactionTemplate, Integer> {}