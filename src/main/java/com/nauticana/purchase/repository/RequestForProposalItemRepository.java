package com.nauticana.purchase.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.purchase.model.RequestForProposalItem;
import com.nauticana.purchase.model.RequestForProposalItemId;

public interface RequestForProposalItemRepository extends JpaRepository<RequestForProposalItem, RequestForProposalItemId>{}
