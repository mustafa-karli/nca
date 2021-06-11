package com.nauticana.purchase.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.purchase.model.PurchaseReason;
import com.nauticana.purchase.model.PurchaseReasonId;

public interface PurchaseReasonRepository extends JpaRepository<PurchaseReason, PurchaseReasonId>{}
