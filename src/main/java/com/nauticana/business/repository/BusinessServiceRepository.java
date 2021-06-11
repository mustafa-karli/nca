package com.nauticana.business.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.business.model.BusinessService;

public interface BusinessServiceRepository extends JpaRepository<BusinessService, String>{}
