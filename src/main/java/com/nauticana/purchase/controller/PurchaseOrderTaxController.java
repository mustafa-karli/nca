package com.nauticana.purchase.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.purchase.model.PurchaseOrderTax;
import com.nauticana.purchase.model.PurchaseOrderTaxId;

@Controller
@ResponseBody
@RequestMapping("/" + PurchaseOrderTax.ROOTMAPPING)
public class PurchaseOrderTaxController extends AbstractController<PurchaseOrderTax, PurchaseOrderTaxId> {}