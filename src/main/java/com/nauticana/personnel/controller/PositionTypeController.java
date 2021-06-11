package com.nauticana.personnel.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.personnel.model.PositionType;
import com.nauticana.personnel.model.PositionTypeId;

@Controller
@ResponseBody
@RequestMapping("/" + PositionType.ROOTMAPPING)
public class PositionTypeController extends AbstractController<PositionType, PositionTypeId> {}

