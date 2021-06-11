package com.nauticana.maintenance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.maintenance.model.MaintenanceStep;
import com.nauticana.maintenance.model.MaintenanceStepId;

public interface MaintenanceStepRepository extends JpaRepository<MaintenanceStep, MaintenanceStepId>{}
