package com.nauticana.maintenance.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.maintenance.model.MaintenanceStep;
import com.nauticana.maintenance.model.MaintenanceStepId;

@Controller
@ResponseBody
@RequestMapping("/" + MaintenanceStep.ROOTMAPPING)
public class MaintenanceStepController extends AbstractController<MaintenanceStep, MaintenanceStepId> {}

