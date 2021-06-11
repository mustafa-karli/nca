package com.nauticana.request.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.request.model.MaterialRequest;

public interface MaterialRequestRepository extends JpaRepository<MaterialRequest, Integer>{}
