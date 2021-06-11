package com.nauticana.personnel.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.personnel.model.PersonQualification;
import com.nauticana.personnel.model.PersonQualificationId;

@Controller
@ResponseBody
@RequestMapping("/" + PersonQualification.ROOTMAPPING)
public class PersonQualificationController extends AbstractController<PersonQualification, PersonQualificationId> {}

