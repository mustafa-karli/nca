package com.nauticana.maintenance.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.maintenance.model.ServiceCharge;
import com.nauticana.maintenance.model.ServiceChargeId;

@Controller
@ResponseBody
@RequestMapping("/" + ServiceCharge.ROOTMAPPING)
public class ServiceChargeController extends AbstractController<ServiceCharge, ServiceChargeId> {}

