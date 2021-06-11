package com.nauticana.motifarge.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.motifarge.model.ProductPriceCommitment;
import com.nauticana.motifarge.model.ProductPriceCommitmentId;

public interface ProductPriceCommitmentRepository extends JpaRepository<ProductPriceCommitment, ProductPriceCommitmentId>{}
