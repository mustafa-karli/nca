package com.nauticana.business.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.business.model.BusinessPartner;

public interface BusinessPartnerRepository extends JpaRepository<BusinessPartner, Integer>{}
