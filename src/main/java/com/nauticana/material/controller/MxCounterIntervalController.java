package com.nauticana.material.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.maintenance.model.MxCounterInterval;
import com.nauticana.maintenance.model.MxCounterIntervalId;

@Controller
@ResponseBody
@RequestMapping("/" + MxCounterInterval.ROOTMAPPING)
public class MxCounterIntervalController extends AbstractController<MxCounterInterval, MxCounterIntervalId> {}

