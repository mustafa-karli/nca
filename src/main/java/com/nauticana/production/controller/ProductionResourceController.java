package com.nauticana.production.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.production.model.ProductionResource;
import com.nauticana.production.model.ProductionResourceId;

@Controller
@ResponseBody
@RequestMapping("/" + ProductionResource.ROOTMAPPING)
public class ProductionResourceController extends AbstractController<ProductionResource, ProductionResourceId> {}