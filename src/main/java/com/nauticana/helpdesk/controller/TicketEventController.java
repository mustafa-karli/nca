package com.nauticana.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.helpdesk.model.TicketEvent;
import com.nauticana.helpdesk.model.TicketEventId;

@Controller
@ResponseBody
@RequestMapping("/ticketEvent")
public class TicketEventController extends AbstractController<TicketEvent, TicketEventId> {}

