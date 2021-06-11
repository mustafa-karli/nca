package com.nauticana.purchase.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.purchase.model.PurchaseOrderTax;
import com.nauticana.purchase.model.PurchaseOrderTaxId;

public interface PurchaseOrderTaxRepository extends JpaRepository<PurchaseOrderTax, PurchaseOrderTaxId>{}
