package com.nauticana.business.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.business.model.VendorBusiness;
import com.nauticana.business.model.VendorBusinessId;

public interface VendorBusinessRepository extends JpaRepository<VendorBusiness, VendorBusinessId>{}
