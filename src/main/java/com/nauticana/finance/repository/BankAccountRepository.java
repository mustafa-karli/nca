package com.nauticana.finance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.finance.model.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, String>{}