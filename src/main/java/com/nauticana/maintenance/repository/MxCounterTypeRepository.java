package com.nauticana.maintenance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.maintenance.model.MxCounterType;

public interface MxCounterTypeRepository extends JpaRepository<MxCounterType, String>{}
