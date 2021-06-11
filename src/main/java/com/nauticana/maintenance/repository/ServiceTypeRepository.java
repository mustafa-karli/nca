package com.nauticana.maintenance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.maintenance.model.ServiceType;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer>{}
