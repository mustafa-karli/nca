package com.nauticana.purchase.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.purchase.model.PurchaseDeliveryLine;
import com.nauticana.purchase.model.PurchaseDeliveryLineId;

public interface PurchaseDeliveryLineRepository extends JpaRepository<PurchaseDeliveryLine, PurchaseDeliveryLineId>{}
