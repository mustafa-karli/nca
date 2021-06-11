package com.nauticana.finance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.finance.model.TaxType;

public interface TaxTypeRepository extends JpaRepository<TaxType, String>{}