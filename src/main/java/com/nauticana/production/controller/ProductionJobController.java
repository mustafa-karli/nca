package com.nauticana.production.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.production.model.ProductionJob;

@Controller
@ResponseBody
@RequestMapping("/" + ProductionJob.ROOTMAPPING)
public class ProductionJobController extends AbstractController<ProductionJob, Integer> {}

