package com.nauticana.business.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.business.model.BusinessOwner;

public interface BusinessOwnerRepository extends JpaRepository<BusinessOwner, Integer>{}
