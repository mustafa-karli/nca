package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.LotInventory;
import com.nauticana.material.model.LotInventoryId;

public interface LotInventoryRepository extends JpaRepository<LotInventory, LotInventoryId>{}
