package com.nauticana.maintenance.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.maintenance.model.Equipment;
import com.nauticana.maintenance.model.EquipmentId;

@Controller
@ResponseBody
@RequestMapping("/" + Equipment.ROOTMAPPING)
public class EquipmentController extends AbstractController<Equipment, EquipmentId> {}

