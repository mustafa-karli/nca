package com.nauticana.maintenance.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.maintenance.model.ServiceType;

@Controller
@ResponseBody
@RequestMapping("/" + ServiceType.ROOTMAPPING)
public class ServiceTypeController extends AbstractController<ServiceType, Integer> {}

