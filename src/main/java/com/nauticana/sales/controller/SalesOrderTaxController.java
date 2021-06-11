package com.nauticana.sales.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.sales.model.SalesOrderTax;
import com.nauticana.sales.model.SalesOrderTaxId;

@Controller
@ResponseBody
@RequestMapping("/" + SalesOrderTax.ROOTMAPPING)
public class SalesOrderTaxController extends AbstractController<SalesOrderTax, SalesOrderTaxId> {}

