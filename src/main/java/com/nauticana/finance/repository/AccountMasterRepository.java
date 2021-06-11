package com.nauticana.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.finance.model.AccountMaster;

public interface AccountMasterRepository extends JpaRepository<AccountMaster, String> {}