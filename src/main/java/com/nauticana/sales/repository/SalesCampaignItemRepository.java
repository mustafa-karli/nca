package com.nauticana.sales.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.sales.model.SalesCampaignItem;
import com.nauticana.sales.model.SalesCampaignItemId;

public interface SalesCampaignItemRepository extends JpaRepository<SalesCampaignItem, SalesCampaignItemId>{}
