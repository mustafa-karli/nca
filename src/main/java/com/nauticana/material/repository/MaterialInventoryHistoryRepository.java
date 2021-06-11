package com.nauticana.material.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.material.model.MaterialInventoryHistory;
import com.nauticana.material.model.MaterialInventoryHistoryId;

public interface MaterialInventoryHistoryRepository extends JpaRepository<MaterialInventoryHistory, MaterialInventoryHistoryId>{}
