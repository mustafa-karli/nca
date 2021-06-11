package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.MaterialSaleTax;
import com.nauticana.material.model.MaterialSaleTaxId;

public interface MaterialSaleTaxRepository extends JpaRepository<MaterialSaleTax, MaterialSaleTaxId>{}
