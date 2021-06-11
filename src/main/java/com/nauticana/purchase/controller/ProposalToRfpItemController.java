package com.nauticana.purchase.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.purchase.model.ProposalToRfpItem;
import com.nauticana.purchase.model.ProposalToRfpItemId;

@Controller
@ResponseBody
@RequestMapping("/proposalToRfpItem")
public class ProposalToRfpItemController extends AbstractController<ProposalToRfpItem, ProposalToRfpItemId> {}