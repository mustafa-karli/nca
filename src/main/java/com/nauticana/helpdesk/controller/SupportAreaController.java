package com.nauticana.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.helpdesk.model.SupportArea;

@Controller
@ResponseBody
@RequestMapping("/supportArea")
public class SupportAreaController extends AbstractController<SupportArea, Integer> {}

