package com.nauticana.personnel.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.personnel.model.PositionQualification;
import com.nauticana.personnel.model.PositionQualificationId;

@Controller
@ResponseBody
@RequestMapping("/" + PositionQualification.ROOTMAPPING)
public class PositionQualificationController extends AbstractController<PositionQualification, PositionQualificationId> {}

