package com.nauticana.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.helpdesk.model.SupportAgent;
import com.nauticana.helpdesk.model.SupportAgentId;

@Controller
@ResponseBody
@RequestMapping("/supportAgent")
public class SupportAgentController extends AbstractController<SupportAgent, SupportAgentId> {}

