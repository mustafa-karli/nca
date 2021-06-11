package com.nauticana.maintenance.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.maintenance.model.EquipmentLocation;
import com.nauticana.maintenance.model.EquipmentLocationId;

@Controller
@ResponseBody
@RequestMapping("/" + EquipmentLocation.ROOTMAPPING)
public class EquipmentLocationController extends AbstractController<EquipmentLocation, EquipmentLocationId> {}

