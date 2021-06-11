package com.nauticana.sales.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.sales.model.SalesCampaign;

public interface SalesCampaignRepository extends JpaRepository<SalesCampaign, Integer>{}
