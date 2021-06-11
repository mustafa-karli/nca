package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.MaterialTypeAttribute;
import com.nauticana.material.model.MaterialTypeAttributeId;

public interface MaterialTypeAttributeRepository extends JpaRepository<MaterialTypeAttribute, MaterialTypeAttributeId>{}
