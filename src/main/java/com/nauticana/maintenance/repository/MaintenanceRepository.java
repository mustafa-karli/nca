package com.nauticana.maintenance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.maintenance.model.Maintenance;
import com.nauticana.maintenance.model.MaintenanceId;

public interface MaintenanceRepository extends JpaRepository<Maintenance, MaintenanceId>{}
