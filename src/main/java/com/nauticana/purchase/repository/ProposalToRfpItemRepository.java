package com.nauticana.purchase.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.purchase.model.ProposalToRfpItem;
import com.nauticana.purchase.model.ProposalToRfpItemId;

public interface ProposalToRfpItemRepository extends JpaRepository<ProposalToRfpItem, ProposalToRfpItemId>{}
