package com.nauticana.material.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.material.model.MaterialGroupAssignment;
import com.nauticana.material.model.MaterialGroupAssignmentId;

@Controller
@ResponseBody
@RequestMapping("/" + MaterialGroupAssignment.ROOTMAPPING)
public class MaterialGroupAssignmentController extends AbstractController<MaterialGroupAssignment, MaterialGroupAssignmentId> {}

