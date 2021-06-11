package com.nauticana.maintenance.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.maintenance.model.Maintenance;
import com.nauticana.maintenance.model.MaintenanceId;

@Controller
@ResponseBody
@RequestMapping("/" + Maintenance.ROOTMAPPING)
public class MaintenanceController extends AbstractController<Maintenance, MaintenanceId> {}

