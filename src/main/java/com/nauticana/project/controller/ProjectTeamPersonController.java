package com.nauticana.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.project.model.ProjectTeamPerson;
import com.nauticana.project.model.ProjectTeamPersonId;

@Controller
@ResponseBody
@RequestMapping("/" + ProjectTeamPerson.ROOTMAPPING)
public class ProjectTeamPersonController extends AbstractController<ProjectTeamPerson, ProjectTeamPersonId>{
//
//	public static final String     module       = "manhour/";
//	public static final String     editView     = module + ProjectTeamPerson.ROOTMAPPING + "Edit";
//
//	@Override
//	public String editView() {return editView;}
}
