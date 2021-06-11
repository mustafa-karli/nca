package com.nauticana.business.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.business.model.ApplicationConfig;

public interface ApplicationConfigRepository extends JpaRepository<ApplicationConfig, Integer>{}
