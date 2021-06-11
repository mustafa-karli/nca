package com.nauticana.business.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.business.model.BusinessContract;
import com.nauticana.business.model.BusinessContractId;

public interface BusinessContractRepository extends JpaRepository<BusinessContract, BusinessContractId>{}
