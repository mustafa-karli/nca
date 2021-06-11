package com.nauticana.personnel.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.personnel.model.Qualification;

@Controller
@ResponseBody
@RequestMapping("/" + Qualification.ROOTMAPPING)
public class QualificationController extends AbstractController<Qualification, Integer> {}

