package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.MaterialAttribute;
import com.nauticana.material.model.MaterialAttributeId;

public interface MaterialAttributeRepository extends JpaRepository<MaterialAttribute, MaterialAttributeId>{}
