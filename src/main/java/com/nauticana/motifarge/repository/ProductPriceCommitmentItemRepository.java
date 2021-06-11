package com.nauticana.motifarge.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.motifarge.model.ProductPriceCommitmentItem;
import com.nauticana.motifarge.model.ProductPriceCommitmentItemId;

public interface ProductPriceCommitmentItemRepository extends JpaRepository<ProductPriceCommitmentItem, ProductPriceCommitmentItemId>{}
