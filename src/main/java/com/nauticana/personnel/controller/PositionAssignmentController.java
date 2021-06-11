package com.nauticana.personnel.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.personnel.model.PositionAssignment;
import com.nauticana.personnel.model.PositionAssignmentId;

@Controller
@ResponseBody
@RequestMapping("/" + PositionAssignment.ROOTMAPPING)
public class PositionAssignmentController extends AbstractController<PositionAssignment, PositionAssignmentId> {}

