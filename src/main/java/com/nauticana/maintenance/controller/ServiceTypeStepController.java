package com.nauticana.maintenance.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.maintenance.model.ServiceTypeStep;
import com.nauticana.maintenance.model.ServiceTypeStepId;

@Controller
@ResponseBody
@RequestMapping("/" + ServiceTypeStep.ROOTMAPPING)
public class ServiceTypeStepController extends AbstractController<ServiceTypeStep, ServiceTypeStepId> {}

