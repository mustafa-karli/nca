package com.nauticana.business.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.business.model.PartnerCertification;

@Controller
@ResponseBody
@RequestMapping("/partnerCertification")
public class PartnerCertificationController extends AbstractController<PartnerCertification, Integer> {}

