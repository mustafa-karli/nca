package com.nauticana.finance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.finance.model.Bank;

public interface BankRepository extends JpaRepository<Bank, String>{}