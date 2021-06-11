package com.nauticana.request.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.request.model.MaterialRequestItem;
import com.nauticana.request.model.MaterialRequestItemId;

public interface MaterialRequestItemRepository extends JpaRepository<MaterialRequestItem, MaterialRequestItemId>{}
