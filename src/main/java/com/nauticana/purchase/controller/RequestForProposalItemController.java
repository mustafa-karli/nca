package com.nauticana.purchase.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.purchase.model.RequestForProposalItem;
import com.nauticana.purchase.model.RequestForProposalItemId;

@Controller
@ResponseBody
@RequestMapping("/requestForProposalItem")
public class RequestForProposalItemController extends AbstractController<RequestForProposalItem, RequestForProposalItemId> {}