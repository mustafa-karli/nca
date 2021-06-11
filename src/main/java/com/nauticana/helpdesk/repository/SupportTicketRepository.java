package com.nauticana.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.helpdesk.model.SupportTicket;

public interface SupportTicketRepository extends JpaRepository<SupportTicket, Integer>{}
