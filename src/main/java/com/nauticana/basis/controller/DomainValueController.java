package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.DomainValue;
import com.nauticana.basis.model.DomainValueId;

@Controller
@ResponseBody
@RequestMapping("/" + DomainValue.ROOTMAPPING)
public class DomainValueController extends AbstractController<DomainValue, DomainValueId> {}
