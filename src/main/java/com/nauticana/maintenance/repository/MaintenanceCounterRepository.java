package com.nauticana.maintenance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.maintenance.model.MaintenanceCounter;
import com.nauticana.maintenance.model.MaintenanceCounterId;

public interface MaintenanceCounterRepository extends JpaRepository<MaintenanceCounter, MaintenanceCounterId>{}
