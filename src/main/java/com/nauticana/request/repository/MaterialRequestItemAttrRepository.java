package com.nauticana.request.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.request.model.MaterialRequestItemAttr;
import com.nauticana.request.model.MaterialRequestItemAttrId;

public interface MaterialRequestItemAttrRepository extends JpaRepository<MaterialRequestItemAttr, MaterialRequestItemAttrId>{}
