package com.nauticana.production.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.production.model.ProductionOrderItem;
import com.nauticana.production.model.ProductionOrderItemId;

@Controller
@ResponseBody
@RequestMapping("/" + ProductionOrderItem.ROOTMAPPING)
public class ProductionOrderItemController extends AbstractController<ProductionOrderItem, ProductionOrderItemId> {}