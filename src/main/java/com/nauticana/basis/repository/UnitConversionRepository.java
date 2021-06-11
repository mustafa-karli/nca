package com.nauticana.basis.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.UnitConversion;
import com.nauticana.basis.model.UnitConversionId;

public interface UnitConversionRepository extends JpaRepository<UnitConversion, UnitConversionId>{

}

