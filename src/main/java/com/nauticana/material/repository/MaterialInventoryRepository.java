package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.MaterialInventory;
import com.nauticana.material.model.MaterialInventoryId;

public interface MaterialInventoryRepository extends JpaRepository<MaterialInventory, MaterialInventoryId>{}
