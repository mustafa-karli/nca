package com.nauticana.production.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.production.model.ProductionLineReason;
import com.nauticana.production.model.ProductionLineReasonId;

@Controller
@ResponseBody
@RequestMapping("/" + ProductionLineReason.ROOTMAPPING)
public class ProductionLineReasonController extends AbstractController<ProductionLineReason, ProductionLineReasonId> {}

