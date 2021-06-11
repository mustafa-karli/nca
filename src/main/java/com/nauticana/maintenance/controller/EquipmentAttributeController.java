package com.nauticana.maintenance.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.maintenance.model.EquipmentAttribute;
import com.nauticana.maintenance.model.EquipmentAttributeId;

@Controller
@ResponseBody
@RequestMapping("/" + EquipmentAttribute.ROOTMAPPING)
public class EquipmentAttributeController extends AbstractController<EquipmentAttribute, EquipmentAttributeId> {}

