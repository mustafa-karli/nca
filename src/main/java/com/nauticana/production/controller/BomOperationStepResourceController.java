package com.nauticana.production.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.production.model.BomOperationStepResource;
import com.nauticana.production.model.BomOperationStepResourceId;

@Controller
@ResponseBody
@RequestMapping("/" + BomOperationStepResource.ROOTMAPPING)
public class BomOperationStepResourceController extends AbstractController<BomOperationStepResource, BomOperationStepResourceId> {}

