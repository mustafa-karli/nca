package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.MaterialType;

public interface MaterialTypeRepository extends JpaRepository<MaterialType, String>{}
