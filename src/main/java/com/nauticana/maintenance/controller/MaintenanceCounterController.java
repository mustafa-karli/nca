package com.nauticana.maintenance.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.maintenance.model.MaintenanceCounter;
import com.nauticana.maintenance.model.MaintenanceCounterId;

@Controller
@ResponseBody
@RequestMapping("/" + MaintenanceCounter.ROOTMAPPING)
public class MaintenanceCounterController extends AbstractController<MaintenanceCounter, MaintenanceCounterId> {}

