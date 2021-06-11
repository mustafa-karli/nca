package com.nauticana.personnel.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.personnel.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer>{}
