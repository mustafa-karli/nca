package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.MaterialSalePrice;
import com.nauticana.material.model.MaterialSalePriceId;

public interface MaterialSalePriceRepository extends JpaRepository<MaterialSalePrice, MaterialSalePriceId>{}
