package com.nauticana.production.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.production.model.BomOperationStep;
import com.nauticana.production.model.BomOperationStepId;

@Controller
@ResponseBody
@RequestMapping("/" + BomOperationStep.ROOTMAPPING)
public class BomOperationStepController extends AbstractController<BomOperationStep, BomOperationStepId> {}

