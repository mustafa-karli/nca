package com.nauticana.purchase.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.purchase.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer>{}
