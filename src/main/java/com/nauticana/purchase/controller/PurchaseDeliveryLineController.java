package com.nauticana.purchase.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.purchase.model.PurchaseDeliveryLine;
import com.nauticana.purchase.model.PurchaseDeliveryLineId;

@Controller
@ResponseBody
@RequestMapping("/" + PurchaseDeliveryLine.ROOTMAPPING)
public class PurchaseDeliveryLineController extends AbstractController<PurchaseDeliveryLine, PurchaseDeliveryLineId> {}