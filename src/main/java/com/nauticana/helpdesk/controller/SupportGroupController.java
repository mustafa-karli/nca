package com.nauticana.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.helpdesk.model.SupportGroup;

@Controller
@ResponseBody
@RequestMapping("/supportGroup")
public class SupportGroupController extends AbstractController<SupportGroup, Integer> {}

