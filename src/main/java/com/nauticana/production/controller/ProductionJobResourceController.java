package com.nauticana.production.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.production.model.ProductionJobResource;
import com.nauticana.production.model.ProductionJobResourceId;

@Controller
@ResponseBody
@RequestMapping("/" + ProductionJobResource.ROOTMAPPING)
public class ProductionJobResourceController extends AbstractController<ProductionJobResource, ProductionJobResourceId> {}

