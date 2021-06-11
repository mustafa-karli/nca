package com.nauticana.project.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.business.model.Subcontractor;
import com.nauticana.project.model.Category;
import com.nauticana.project.model.ProjectTeam;
import com.nauticana.project.model.ProjectTeamId;
import com.nauticana.project.model.ProjectTeamTemplate;
import com.nauticana.project.model.ProjectTeamTemplateId;
import com.nauticana.project.model.ProjectWbs;
import com.nauticana.project.model.ProjectWbsId;
import com.nauticana.project.service.ManhourJdbcService;
import com.nauticana.project.service.ProjectTeamService;
import com.nauticana.project.service.ProjectWbsService;

@Controller
@ResponseBody
@RequestMapping("/" + ProjectTeamTemplate.ROOTMAPPING)
public class ProjectTeamTemplateController extends AbstractController<ProjectTeamTemplate, ProjectTeamTemplateId>{

	@Autowired
	private ProjectWbsService projectWbsService;
	
	@Autowired
	private ProjectTeamService projectTeamService;

	@Autowired
	private ManhourJdbcService manhourJdbcService;

//	public static final String module     = "manhour/";
//	public static final String selectView = module + ProjectTeamTemplate.ROOTMAPPING + "Select";

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ModelAndView selectGet(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());
		
		String[] id = null;
		int projectId=0, teamId = 0;
		try {
			id = request.getParameter("id").split(",");
			projectId= Integer.parseInt(id[0]);
			teamId= Integer.parseInt(id[1]);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, tableName() + " : projectId,teamId");
		}
		String prevpage= "projectTeam/show?id=" + projectId + "," + teamId;
		ModelAndView model = selectView(getControllerStatic(), language);
		String wbshtml = manhourJdbcService.findAllCategoryHtml(projectId, teamId, language.code);
		model.addObject("wbshtml", wbshtml);
		model.addObject("projectId", projectId);
		model.addObject("teamId", teamId);
		
		// Assign text objects from session language
		model.addObject(Labels.PREVPAGE, prevpage);
		model.addObject(Labels.PAGETITLE, language.getText(Category.TABLE_NAME));
		model.addObject(Labels.SELECT, language.getIconText(Labels.SELECT));
		model.addObject(Labels.OK, language.getIconText(Labels.OK));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PERSON_ID, language.getIconText(Labels.PERSON_ID));
		model.addObject(Subcontractor.TABLE_NAME, language.getText(Subcontractor.TABLE_NAME));
//		for (int i = 0; i < Category.FIELD_NAMES.length; i++) {
//			model.addObject(Category.FIELD_NAMES[i], language.getText(Category.FIELD_NAMES[i]));
//		}
		return model;
	}
	
	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public ModelAndView selectPost(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		int projectId=0, teamId = 0;
		try {
			projectId= Integer.parseInt(request.getParameter("projectId"));
			teamId= Integer.parseInt(request.getParameter("teamId"));
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, tableName() + " : projectId,teamId");
		}
		
//		System.out.println("Project Id : " + projectId);
//		System.out.println("Team Id : " + teamId);
		String s = request.getParameter("WBS_IDS");
//		System.out.println("Project WBSes : " + s);
		if (!Utils.emptyStr(s)) {
			String[] wid = s.split(",");
			for (int i = 1; i < wid.length; i++) {
//				System.out.println("Processing : " + wid[i]);
				try {
					int w = Integer.parseInt(wid[i]);
					ProjectTeamTemplateId id = new ProjectTeamTemplateId(projectId, w, teamId);
					ProjectTeamTemplate ptt = modelService.findById(id);
					if (ptt == null) {
//						System.out.println("Inserting Project Team Template : " + projectId + " " + teamId + " : " + w);
						ProjectWbs pw = projectWbsService.findById(new ProjectWbsId(projectId, w));
						ProjectTeam pt = projectTeamService.findById(new ProjectTeamId(projectId, teamId));
						ptt = new ProjectTeamTemplate();
						ptt.setId(id);
						ptt.setProjectTeam(pt);
						ptt.setProjectWbs(pw);
						modelService.create(ptt);
					}
				} catch (Exception e) {
				}
			}
		}
		
//		System.out.println("select Post Method");
		
		return new ModelAndView("redirect:/projectTeam/show?id=" + projectId + "," + teamId);
	}
}
