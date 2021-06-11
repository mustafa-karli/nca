package com.nauticana.purchase.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.purchase.model.PurchaseOrderItem;
import com.nauticana.purchase.model.PurchaseOrderItemId;

@Controller
@ResponseBody
@RequestMapping("/" + PurchaseOrderItem.ROOTMAPPING)
public class PurchaseOrderItemController extends AbstractController<PurchaseOrderItem, PurchaseOrderItemId> {}