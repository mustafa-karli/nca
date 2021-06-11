package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.DomainLookup;
import com.nauticana.basis.model.DomainLookupId;

@Controller
@ResponseBody
@RequestMapping("/" + DomainLookup.ROOTMAPPING)
public class DomainLookupController extends AbstractController<DomainLookup, DomainLookupId>{}
