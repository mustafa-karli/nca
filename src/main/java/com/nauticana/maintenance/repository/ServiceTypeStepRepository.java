package com.nauticana.maintenance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.maintenance.model.ServiceTypeStep;
import com.nauticana.maintenance.model.ServiceTypeStepId;

public interface ServiceTypeStepRepository extends JpaRepository<ServiceTypeStep, ServiceTypeStepId>{}
