package com.nauticana.purchase.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.purchase.model.PurchaseOrderItemAttr;
import com.nauticana.purchase.model.PurchaseOrderItemAttrId;

public interface PurchaseOrderItemAttrRepository extends JpaRepository<PurchaseOrderItemAttr, PurchaseOrderItemAttrId>{}
