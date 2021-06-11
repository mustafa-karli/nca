package com.nauticana.purchase.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.purchase.model.PurchaseDelivery;

@Controller
@ResponseBody
@RequestMapping("/" + PurchaseDelivery.ROOTMAPPING)
public class PurchaseDeliveryController extends AbstractController<PurchaseDelivery, Integer> {}