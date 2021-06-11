package com.nauticana.sales.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.sales.model.SalesOrderItem;
import com.nauticana.sales.model.SalesOrderItemId;

@Controller
@ResponseBody
@RequestMapping("/salesOrderItem")
public class SalesOrderItemController extends AbstractController<SalesOrderItem, SalesOrderItemId> {}