package com.nauticana.purchase.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.purchase.model.PurchaseReason;
import com.nauticana.purchase.model.PurchaseReasonId;

@Controller
@ResponseBody
@RequestMapping("/" + PurchaseReason.ROOTMAPPING)
public class PurchaseReasonController extends AbstractController<PurchaseReason, PurchaseReasonId> {}