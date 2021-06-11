package com.nauticana.maintenance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.maintenance.model.ServiceCharge;
import com.nauticana.maintenance.model.ServiceChargeId;

public interface ServiceChargeRepository extends JpaRepository<ServiceCharge, ServiceChargeId>{}
