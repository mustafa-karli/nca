package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.MasterDetailRelation;

@Controller
@ResponseBody
@RequestMapping("/masterDetailRelation")
public class MasterDetailRelationController extends AbstractController<MasterDetailRelation, String>{}
