package com.nauticana.maintenance.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.maintenance.model.FinalEquipmentPart;
import com.nauticana.maintenance.model.FinalEquipmentPartId;

@Controller
@ResponseBody
@RequestMapping("/" + FinalEquipmentPart.ROOTMAPPING)
public class FinalEquipmentPartController extends AbstractController<FinalEquipmentPart, FinalEquipmentPartId> {}

