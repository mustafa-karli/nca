package com.nauticana.personnel.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.personnel.model.Position;
import com.nauticana.personnel.model.PositionId;

@Controller
@ResponseBody
@RequestMapping("/" + Position.ROOTMAPPING)
public class PositionController extends AbstractController<Position, PositionId> {}

