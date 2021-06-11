package com.nauticana.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.helpdesk.model.SupportAgent;
import com.nauticana.helpdesk.model.SupportAgentId;

public interface SupportAgentRepository extends JpaRepository<SupportAgent, SupportAgentId>{}