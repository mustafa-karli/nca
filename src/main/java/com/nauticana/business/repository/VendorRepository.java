package com.nauticana.business.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.business.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer>{}
