package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.MaterialAttributeOption;
import com.nauticana.material.model.MaterialAttributeOptionId;

public interface MaterialAttributeOptionRepository extends JpaRepository<MaterialAttributeOption, MaterialAttributeOptionId>{}
