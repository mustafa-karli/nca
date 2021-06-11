package com.nauticana.sales.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.sales.model.SalesOrderItemAttr;
import com.nauticana.sales.model.SalesOrderItemAttrId;

@Controller
@ResponseBody
@RequestMapping("/" + SalesOrderItemAttr.ROOTMAPPING)
public class SalesOrderItemAttrController extends AbstractController<SalesOrderItemAttr, SalesOrderItemAttrId> {}