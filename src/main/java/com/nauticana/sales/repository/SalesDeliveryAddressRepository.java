package com.nauticana.sales.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.sales.model.SalesDeliveryAddress;

public interface SalesDeliveryAddressRepository extends JpaRepository<SalesDeliveryAddress, Integer>{}
