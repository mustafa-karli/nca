package com.nauticana.personnel.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.personnel.model.Organization;

@Controller
@ResponseBody
@RequestMapping("/" + Organization.ROOTMAPPING)
public class OrganizationController extends AbstractController<Organization, Integer> {}

