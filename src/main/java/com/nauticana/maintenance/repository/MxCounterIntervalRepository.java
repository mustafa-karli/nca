package com.nauticana.maintenance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.maintenance.model.MxCounterInterval;
import com.nauticana.maintenance.model.MxCounterIntervalId;

public interface MxCounterIntervalRepository extends JpaRepository<MxCounterInterval, MxCounterIntervalId>{}
