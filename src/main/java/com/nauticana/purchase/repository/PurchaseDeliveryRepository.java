package com.nauticana.purchase.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.purchase.model.PurchaseDelivery;

public interface PurchaseDeliveryRepository extends JpaRepository<PurchaseDelivery, Integer>{}
