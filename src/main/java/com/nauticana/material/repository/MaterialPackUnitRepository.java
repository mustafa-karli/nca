package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.MaterialPackUnit;
import com.nauticana.material.model.MaterialPackUnitId;

public interface MaterialPackUnitRepository extends JpaRepository<MaterialPackUnit, MaterialPackUnitId>{}
