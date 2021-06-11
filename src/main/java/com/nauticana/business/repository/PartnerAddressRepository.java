package com.nauticana.business.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.business.model.PartnerAddress;
import com.nauticana.business.model.PartnerAddressId;

public interface PartnerAddressRepository extends JpaRepository<PartnerAddress, PartnerAddressId>{}
