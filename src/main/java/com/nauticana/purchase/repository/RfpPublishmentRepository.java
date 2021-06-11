package com.nauticana.purchase.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.purchase.model.RfpPublishment;
import com.nauticana.purchase.model.RfpPublishmentId;

public interface RfpPublishmentRepository extends JpaRepository<RfpPublishment, RfpPublishmentId>{}
