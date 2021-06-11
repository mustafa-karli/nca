package com.nauticana.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.helpdesk.model.TicketEvent;
import com.nauticana.helpdesk.model.TicketEventId;

public interface TicketEventRepository extends JpaRepository<TicketEvent, TicketEventId>{}
