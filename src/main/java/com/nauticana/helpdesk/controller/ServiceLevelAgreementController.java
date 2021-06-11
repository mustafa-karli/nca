package com.nauticana.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.helpdesk.model.ServiceLevelAgreement;
import com.nauticana.helpdesk.model.ServiceLevelAgreementId;

@Controller
@ResponseBody
@RequestMapping("/serviceLevelAggrement")
public class ServiceLevelAgreementController extends AbstractController<ServiceLevelAgreement, ServiceLevelAgreementId> {}

