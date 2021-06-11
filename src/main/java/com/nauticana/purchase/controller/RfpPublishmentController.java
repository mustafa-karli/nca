package com.nauticana.purchase.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.purchase.model.RfpPublishment;
import com.nauticana.purchase.model.RfpPublishmentId;

@Controller
@ResponseBody
@RequestMapping("/rfpPublishment")
public class RfpPublishmentController extends AbstractController<RfpPublishment, RfpPublishmentId> {}