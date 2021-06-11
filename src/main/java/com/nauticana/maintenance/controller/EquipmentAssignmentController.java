package com.nauticana.maintenance.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.maintenance.model.EquipmentAssignment;
import com.nauticana.maintenance.model.EquipmentAssignmentId;

@Controller
@ResponseBody
@RequestMapping("/" + EquipmentAssignment.ROOTMAPPING)
public class EquipmentAssignmentController extends AbstractController<EquipmentAssignment, EquipmentAssignmentId> {}

