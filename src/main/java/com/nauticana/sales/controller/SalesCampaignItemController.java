package com.nauticana.sales.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.sales.model.SalesCampaignItem;
import com.nauticana.sales.model.SalesCampaignItemId;

@Controller
@ResponseBody
@RequestMapping("/" + SalesCampaignItem.ROOTMAPPING)
public class SalesCampaignItemController extends AbstractController<SalesCampaignItem, SalesCampaignItemId> {}