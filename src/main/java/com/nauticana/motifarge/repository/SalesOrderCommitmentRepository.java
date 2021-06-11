package com.nauticana.motifarge.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.motifarge.model.SalesOrderCommitment;
import com.nauticana.motifarge.model.SalesOrderCommitmentId;

public interface SalesOrderCommitmentRepository extends JpaRepository<SalesOrderCommitment, SalesOrderCommitmentId>{}