package com.nauticana.project.controller;

import java.io.IOException;
import java.util.List;

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
import com.nauticana.project.model.Category;
import com.nauticana.project.model.Project;
import com.nauticana.project.model.ProjectWbs;
import com.nauticana.project.model.ProjectWbsId;
import com.nauticana.project.service.CategoryService;
import com.nauticana.project.service.ManhourJdbcService;
import com.nauticana.project.service.ProjectService;

@Controller
@ResponseBody
@RequestMapping("/" + ProjectWbs.ROOTMAPPING)
public class ProjectWbsController extends AbstractController<ProjectWbs, ProjectWbsId>{

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProjectService projectServices;

	@Autowired
	private ManhourJdbcService manhourJdbcService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ModelAndView selectGet(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		List<String> ids = basisService.authorizedIds(username, Labels.PROJECT_TEAM, Labels.APPROVE_MANHOUR);
		List<Project> projects = projectServices.findByIds(ids);
		if (projects.size() == 0)
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.PROJECT_TEAM + "-" + Labels.APPROVE_MANHOUR);
		
		
		
		int projectId=0;
		
		String strId = request.getParameter("projectId");
		
		if (!Utils.emptyStr(strId))
				projectId = Integer.parseInt(strId);
		else {
			try {
				projectId= Integer.parseInt(request.getParameter("id"));
			} catch (Exception e) {
				return errorPage(language, Labels.ERR_PARAMETER_MISSING, " projectId");
			}
		}
		
        ModelAndView model = selectView(getControllerStatic(), language);

        strId = request.getParameter("projectFilter");
		int projectFilter = projectId;
		if (!Utils.emptyStr(strId)) {
			model.addObject("projectFilter", strId);
			projectFilter =Integer.parseInt(strId);
		}

		String[] links = new String[] {"category/new?projectId=" + projectId + "&parentKey="};
		String[] caps  = new String[] {"<i class=\"fa fa-angle-double-right\"></i>" +  language.getIconText(Labels.NEW), language.getIconText(Labels.EDIT), language.getIconText(Labels.DELETE)};
		
		String wbshtml = manhourJdbcService.findAllCategoryHtml(projectId, projectFilter, "tv3", links, caps, language.code);
		String prevpage = "project/show?id="+ projectId;
//		System.out.println(wbshtml);
		model.addObject("wbshtml", wbshtml);
		model.addObject("projectId", projectId);
		
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(ProjectWbs.TABLE_NAME));
		model.addObject(Labels.SELECT, language.getIconText(Labels.SELECT));
		model.addObject(Labels.OK, language.getIconText(Labels.OK));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.SELECT_ALL, language.getIconText(Labels.SELECT_ALL));
		model.addObject(Labels.DE_SELECT, language.getIconText(Labels.DE_SELECT));
		model.addObject(Labels.TOGGLE_SELECT, language.getIconText(Labels.TOGGLE_SELECT));
		model.addObject(Labels.NEW, language.getIconText(Labels.NEW));
		model.addObject(Labels.EDIT, language.getIconText(Labels.EDIT));
		model.addObject(Labels.DELETE, language.getIconText(Labels.DELETE));
		model.addObject(Category.TABLE_NAME, language.getText(Category.TABLE_NAME));
		model.addObject(Labels.PREVPAGE,prevpage);
		model.addObject(Labels.CAPTION_FILTER, language.getText(Labels.CAPTION_FILTER));
		model.addObject(Labels.PROJECT_FILTER, language.getText(Labels.PROJECT_FILTER));
		
		
		model.addObject("projects", projects);
		model.addObject("projectId", projectId);
	
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

		int projectId=0;
		try {
			projectId= Integer.parseInt(request.getParameter("projectId"));
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, " projectId");
		}
//		System.out.println("Project Id : " + projectId);
		String s = request.getParameter("WBS_IDS");
//		System.out.println("Project WBSes : " + s);
		if (!Utils.emptyStr(s)) {
			String[] wid = s.split(",");
			for (int i = 1; i < wid.length; i++) {
//				System.out.println("Processing : " + wid[i]);
				int w = Integer.parseInt(wid[i]);
				ProjectWbsId id = new ProjectWbsId(projectId, w);
				ProjectWbs pw = modelService.findById(id);
				if (pw == null) {
//					System.out.println("Inserting Project WBS : " + projectId + " : " + w);
					pw = modelService.newEntityWithParentId(projectId+"");
					Category category = categoryService.findById(w);
					pw.setCategory(category);
					pw.setId(id);
					pw.setMetric(0);
					pw.setQuantity(0);
					String u = category.getUnit();
					if (Utils.emptyStr(u))
						pw.setUnit("m");
					else
						pw.setUnit(u.substring(0,Math.min(3,u.length())));
					try {
						modelService.create(pw);
					} catch(Exception e) {
						return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
					}

				}
			}
		}
		
//		System.out.println("select Post Method");
		return new ModelAndView("redirect:/project/show?id=" + projectId);
	}

}
