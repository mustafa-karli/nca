package com.nauticana.purchase.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.purchase.model.ProposalToRfpDialog;
import com.nauticana.purchase.model.ProposalToRfpDialogId;

public interface ProposalToRfpDialogRepository extends JpaRepository<ProposalToRfpDialog, ProposalToRfpDialogId>{}
