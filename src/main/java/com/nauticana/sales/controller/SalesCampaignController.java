package com.nauticana.sales.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.sales.model.SalesCampaign;

@Controller
@ResponseBody
@RequestMapping("/" + SalesCampaign.ROOTMAPPING)
public class SalesCampaignController extends AbstractController<SalesCampaign, Integer> {}