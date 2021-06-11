package com.nauticana.helpdesk.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.helpdesk.model.ServiceLevelAgreement;
import com.nauticana.helpdesk.model.ServiceLevelAgreementId;

public interface ServiceLevelAgreementRepository extends JpaRepository<ServiceLevelAgreement, ServiceLevelAgreementId>{}
