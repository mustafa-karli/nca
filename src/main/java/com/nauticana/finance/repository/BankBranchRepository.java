package com.nauticana.finance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.finance.model.BankBranch;

public interface BankBranchRepository extends JpaRepository<BankBranch, String>{}