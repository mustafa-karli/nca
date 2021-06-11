package com.nauticana.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.Language;
import com.nauticana.basis.service.LanguageService;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.project.model.Category;
import com.nauticana.project.model.CategoryText;
import com.nauticana.project.model.CategoryTextId;
import com.nauticana.project.model.Project;
import com.nauticana.project.service.CategoryTextService;
import com.nauticana.project.service.ManhourJdbcService;
import com.nauticana.project.service.ProjectService;

@Controller
@ResponseBody
@RequestMapping("/" + Category.ROOTMAPPING)
public class CategoryController extends AbstractController<Category, Integer> {

	public static final String module     = "manhour/";
	public static final String listView   = module + Category.ROOTMAPPING + "List";
	public static final String editView   = module + Category.ROOTMAPPING + "Edit";
	
	@Autowired
	public ProjectService projectService;

	@Autowired
	private ManhourJdbcService manhourJdbcService;

	@Autowired
	private LanguageService languageService;

	@Autowired
	private CategoryTextService categoryTextService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listGet(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());
		
		List<String> ids = basisService.authorizedIds(username, Labels.PROJECT_TEAM, Labels.APPROVE_MANHOUR);
		List<Project> projects = projectService.findByIds(ids);
		
		String strId = request.getParameter("projectId");
		int projectId = -1;
		if (!Utils.emptyStr(strId))
			projectId = Integer.parseInt(strId);

        ModelAndView model = listView(getControllerStatic(), language);
		
		model.addObject("projects", projects);
		model.addObject("projectId", projectId);
		
		model.addObject(Labels.PAGETITLE, language.getText(tableName()));
		model.addObject(Labels.NEW, language.getIconText(Labels.NEW));
		model.addObject(Labels.EDIT, language.getIconText(Labels.EDIT));
		model.addObject(Labels.DELETE, language.getIconText(Labels.DELETE));
		model.addObject(Labels.CAPTION_FILTER, language.getText(Labels.CAPTION_FILTER));
		model.addObject(Labels.PROJECT_FILTER, language.getText(Labels.PROJECT_FILTER));
		String[] links = new String[] {"category/new?parentKey=", "category/edit?id=", "category/delete?id="};
		String[] caps  = new String[] {"<i class=\"fa fa-angle-double-right\"></i>" +  language.getIconText(Labels.NEW), language.getIconText(Labels.EDIT), language.getIconText(Labels.DELETE)};
		String wbshtml = manhourJdbcService.findAllCategoryHtml(projectId, projectId, "tv3", links, caps, language.code);
		model.addObject("wbshtml", wbshtml);
		return model;
	}
	
	@Override
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newGet(HttpServletRequest request) {
		ModelAndView model = super.newGet(request);

		List<Language> l = languageService.findAll();
		model.addObject("languages", l);
		
		String projectId = request.getParameter("projectId");
		if (!Utils.emptyStr(projectId)) {
			Category record = (Category) model.getModel().get("record");
			record.setProjectId(Integer.parseInt(projectId));
			String prevpage = "projectWbs/select?id=" + projectId;
			model.addObject(Labels.NEXTPAGE,prevpage);
			model.getModel().replace(Labels.PREVPAGE, prevpage) ;
		}

		return model;
	}

	@Override
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editGet(HttpServletRequest request) {
		ModelAndView model = super.editGet(request);

		List<Language> l = languageService.findAll();
		Category record = (Category) model.getModel().get("record");

		for (CategoryText ct : record.getCategoryTexts()) {
			for (int i = l.size()-1; i >= 0; i--) {
				if (l.get(i).getId().equals(ct.getId().getLangcode()))
					l.remove(i);
			}
		}
		
		model.addObject("languages", l);
		return model;
	}

	@Override
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editPost(@ModelAttribute Category record, BindingResult result, HttpServletRequest request) {

		// Check for user and insert/update authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.UPDATE, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.UPDATE + " ON " + tableName());

		if (result.hasErrors()) {
			String msgText = "";
			for (ObjectError e : result.getAllErrors()) {
				msgText = msgText + "\n" + e.toString();
			}
			return errorPage(language, Labels.ERR_BINDING, msgText);
		}

		// Update record in database
		try {
			modelService.save(record);
		} catch(Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}

		boolean k = true;
		int i = 0;
		while (k) {
			String lang = request.getParameter("lang"+i);
			String caption = request.getParameter("caption"+i);
			i++;
			if (Utils.emptyStr(lang))
				k = false;
			else {
				try {
					CategoryTextId textId = new CategoryTextId(record.getId(), lang);
					CategoryText ct = categoryTextService.findById(textId);
					if (ct != null) {
						if (Utils.emptyStr(caption)) {
							categoryTextService.remove(textId);
						} else {
							ct.setCaption(caption);
							categoryTextService.save(ct);
						}
					}
				} catch(Exception e) {
					return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
				}
			}
		}
		k = true;
		i = 0;
		while (k) {
			String lang = request.getParameter("newLang"+i);
			String caption = request.getParameter("newCaption"+i);
			i++;
			if (Utils.emptyStr(lang))
				k = false;
			else if (!Utils.emptyStr(caption)) {
				CategoryText ct = categoryTextService.newEntityWithParentId(record.getId()+","+lang);
				if (ct != null) {
					ct.setCaption(caption);
					try {
						categoryTextService.save(ct);
					} catch(Exception e) {
						return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
					}
				}
			}
		}
		
		String nextpage = request.getParameter(Labels.NEXTPAGE);
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:/" + getControllerStatic().getRootMapping() + "/list");
		else
			return new ModelAndView("redirect:/" + nextpage);
	}


	@Override
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView newPost(@ModelAttribute Category record, BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		if (result.hasErrors()) {
			String msgText = "";
			for (ObjectError e : result.getAllErrors()) {
				msgText = msgText + "\n" + e.toString();
			}
			return errorPage(language, Labels.ERR_BINDING, msgText);
		}
		try {
			modelService.save(record);
		} catch(Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}

		boolean k = true;
		int i = 0;
		while (k) {
			String lang = request.getParameter("newLang"+i);
			String caption = request.getParameter("newCaption"+i);
			i++;
			if (Utils.emptyStr(lang))
				k = false;
			else {
				CategoryText ct = categoryTextService.newEntityWithParentId(record.getId()+","+lang);
				if (ct != null) {
					ct.setCaption(caption);
					try {
						categoryTextService.save(ct);
					} catch(Exception e) {
						return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
					}
				}
			}
		}
		
		String nextpage = request.getParameter(Labels.NEXTPAGE);
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:list");
		else
			return new ModelAndView("redirect:/" + nextpage);
	}
	
	

}
