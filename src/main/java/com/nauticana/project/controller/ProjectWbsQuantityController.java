package com.nauticana.project.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
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
import com.nauticana.basis.utils.Icons;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.project.model.Project;
import com.nauticana.project.model.ProjectWbs;
import com.nauticana.project.model.ProjectWbsId;
import com.nauticana.project.model.ProjectWbsManhour;
import com.nauticana.project.model.ProjectWbsQuantity;
import com.nauticana.project.model.ProjectWbsQuantityId;
import com.nauticana.project.model.ViewProjectWbsQtyApprove;
import com.nauticana.project.model.ViewWbsTeamStatus;
import com.nauticana.project.service.ManhourJdbcService;
import com.nauticana.project.service.ProjectService;
import com.nauticana.project.service.ProjectWbsQuantityService;
import com.nauticana.project.service.ProjectWbsService;

@Controller
@ResponseBody
@RequestMapping("/" + ProjectWbsQuantity.ROOTMAPPING)
public class ProjectWbsQuantityController extends AbstractController<ProjectWbsQuantity, ProjectWbsQuantityId> {

	public static final String module     = "manhour/";
	public static final String editView   = module + ProjectWbsQuantity.ROOTMAPPING + "Edit";
	public static final String selectView = module + ProjectWbsQuantity.ROOTMAPPING + "Select";

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectWbsService projectWbsService;

	@Autowired
	private ManhourJdbcService manhourJdbcService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ModelAndView selectGet(HttpServletRequest request) throws IOException, ParseException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		List<String> ids = basisService.authorizedIds(username, Labels.PROJECT_TEAM, Labels.APPROVE_QUANTITY);
		List<Project> projects = projectService.findByIds(ids);
//		System.out.println("projects : " + projects.size() + " projects");
		if (projects.size() == 0)
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.PROJECT_TEAM + "-" + Labels.APPROVE_QUANTITY);
		ModelAndView model = new ModelAndView(module + "projectWbsQuantityEntry");
		model.addObject("language", language);
		model.addObject("projects", projects);
		int projectId,year,month;
		String strProjectId = request.getParameter("projectId");
		String strYear  = request.getParameter("year");
		String strMonth = request.getParameter("month");
//		System.out.println("Get wbsQuantityEntry projectId " + strProjectId + " year " + strYear + " month " + strMonth);
		Calendar c = Calendar.getInstance();
		try {projectId = Integer.parseInt(strProjectId);} catch (Exception e) {projectId = -1;}//projects.get(0).getId();}
		try {year = Integer.parseInt(strYear);} catch (Exception e) {year = c.get(Calendar.YEAR);}
		try {month = Integer.parseInt(strMonth);} catch (Exception e) {month = c.get(Calendar.MONTH)+1;}
	    c.set(year, month-1, 1, 0, 0, 0);
	    Date begda = c.getTime();
	    c.set(year, month-1, c.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date endda = c.getTime();
		
//		s = request.getParameter("begda");
//		if (Utils.emptyStr(s))
//			model.addObject("begda", Labels.ymdDF.format(new Date(System.currentTimeMillis())));
//		else
//			model.addObject("begda", s);
//
//		s = request.getParameter("endda");
//		if (Utils.emptyStr(s))
//			model.addObject("endda", Labels.ymdDF.format(new Date(System.currentTimeMillis())));
//		else
//			model.addObject("endda", s);

		List<ViewWbsTeamStatus> pwq = manhourJdbcService.viewWbsTeamStatus(projectId, begda, language.code);

		model.addObject("records", pwq);
		model.addObject("MONTHS", dataCache.getDomainOptions("MONTH", language));
		model.addObject("projectId", projectId);
		model.addObject("year", year);
		model.addObject("month", month);
		model.addObject("begda", Labels.dmyDF.format(begda));
		model.addObject("endda", Labels.dmyDF.format(endda));
		model.addObject(Icons.PROJECT_QUANTITY, Icons.getIcon(Icons.PROJECT_QUANTITY));
		model.addObject(Labels.PAGETITLE, language.getText(ProjectWbsQuantity.TABLE_NAME));
		model.addObject(Labels.PREVPAGE,  "projectWbsQuantity/select?projectId=" + projectId + "&year=" + year + "&month=" + month);
		model.addObject(Labels.POSTLINK,  "projectWbsQuantity/select");
		model.addObject("DATATABLE1",     Labels.dataTableSetting1);
		model.addObject(tableName(),      language.getText(tableName()));
		return model;
	}

	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public ModelAndView selectPost(HttpServletRequest request) throws IOException, ParseException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

//		String operation = request.getParameter("operation");
//		System.out.println("Operation : " + operation);
		String nextpage = request.getParameter(Labels.NEXTPAGE);
//		System.out.println("Nextpage : " + nextpage);

		// Read POSTED request variables
		String strProjectId = request.getParameter("projectId");
		String strBegda = request.getParameter("begda");
		String strEndda = request.getParameter("endda");
//		String data = request.getParameter("data");

//		System.out.println("Submitted projectId " + strProjectId + " begda " + strBegda + " endda " + strEndda + "\n data \n" + data);
		
		int projectId = Integer.parseInt(strProjectId);
		Date begda, endda;
		try {begda = Labels.dmyDF.parse(strBegda);} catch (Exception e) {begda = null;}
		try {endda = Labels.dmyDF.parse(strEndda);} catch (Exception e) {endda = null;}
		
		int ind = 0;
		
		String teamStr = request.getParameter("teamId" + ind);
		
		while (!Utils.emptyStr(teamStr)) {
			String catStr = request.getParameter("categoryId" + ind);
			String begStr = request.getParameter("begda" + ind);
			String endStr = request.getParameter("endda" + ind);
			String lastEndStr = request.getParameter("lastEndda" + ind);
			String qtyStr = request.getParameter("quantity" + ind);
			try {
//				System.out.println("Quantity : " + qtyStr);
				float quantity = Float.parseFloat(qtyStr);
//				System.out.println(" Parsed : " + quantity);
				int teamId = Integer.parseInt(teamStr);
//				System.out.println(" Team : " + teamId);
				int categoryId = Integer.parseInt(catStr);
//				System.out.println(" Category : " + categoryId);
				ProjectWbsId pwId = new ProjectWbsId(projectId, categoryId);
				ProjectWbs pw = projectWbsService.findById(pwId);
				Date d1, d2, lastEndda;
				try {lastEndda = Labels.dmyDF.parse(lastEndStr);} catch (Exception e) {lastEndda = null;};
				try {d1 = Labels.dmyDF.parse(begStr);} catch (Exception e) {d1 = begda;};
				try {d2 = Labels.dmyDF.parse(endStr);} catch (Exception e) {d2 = endda;};
				if (d1 == null || d2 == null)
					return errorPage(language, Labels.ERR_WRONGDATA, "BEGDA or ENDDA is null ON " + tableName());
				if (lastEndda != null && d1.before(lastEndda))
					return errorPage(language, Labels.ERR_WRONGDATA, "BEGDA " + d1.toString() + " before last record at " + lastEndda.toString() + " ON " + tableName());
				if (d1.after(d2))
					return errorPage(language, Labels.ERR_WRONGDATA, "BEGDA " + d1.toString() + " after ENDDA at " + d2.toString() + " ON " + tableName());
				ProjectWbsQuantityId id = new ProjectWbsQuantityId(projectId, categoryId, teamId, d1);
				ProjectWbsQuantity pwq = modelService.findById(id);
				if (pwq == null) {
					pwq = new ProjectWbsQuantity();
					pwq.setId(id);
					pwq.setProjectWbs(pw);
					pwq.setEndda(d2);
					pwq.setQuantity(quantity);
					pwq.setStatus(Labels.INITIAL);
					modelService.create(pwq);
				} else if (pwq.getQuantity() != quantity) {
					pwq.setQuantity(quantity);
					modelService.save(pwq);
				}
			} catch (Exception e) {
				
			}
			ind++;
			teamStr = request.getParameter("teamId" + ind);
		}
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:select?projectId=" + projectId);
		else
			return new ModelAndView("redirect:/" + nextpage);
	}

	@RequestMapping(value = "/approve", method = RequestMethod.GET)
	public ModelAndView approveGet(HttpServletRequest request) throws IOException, ParseException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

//		String operation = request.getParameter("operation");
//		System.out.println("Operation : " + operation);
//		String nextpage = request.getParameter(Labels.NEXTPAGE);
//		System.out.println("Nextpage : " + nextpage);

		List<String> ids = basisService.authorizedIds(username, Labels.PROJECT_TEAM, Labels.APPROVE_QUANTITY);
		List<Project> projects = projectService.findByIds(ids);
//		System.out.println("projects : " + projects.size() + " projects");
		if (projects.size() == 0)
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.PROJECT_TEAM + "-" + Labels.APPROVE_QUANTITY);
		int projectId = projects.get(0).getId();
		String strId = request.getParameter("projectId");
		if (!Utils.emptyStr(strId))
			projectId = Integer.parseInt(strId);
		List<ViewProjectWbsQtyApprove> records = manhourJdbcService.getProjectWbsQtyApprove(projectId, language.code);
		ModelAndView model = new ModelAndView(module + "projectWbsQuantityApprove");
		model.addObject("language", language);
		model.addObject("projects", projects);
		model.addObject("projectId", projectId);
		model.addObject("records", records);
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(ProjectWbsManhour.TABLE_NAME));
		model.addObject(Labels.SELECT, language.getIconText(Labels.SELECT));
		model.addObject(Labels.OK, language.getIconText(Labels.OK));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.TENDER,    language.getText(Labels.TENDER));
		model.addObject(Labels.APPROVE_ALL, language.getIconText(Labels.APPROVE_ALL));
		model.addObject(Labels.APPROVE_QUANTITY, language.getIconText(Labels.APPROVE_QUANTITY));
		model.addObject(Labels.dataTableNames[0], Labels.dataTableSettings[0]);
		model.addObject(ViewProjectWbsQtyApprove.tableName, language.getText(ViewProjectWbsQtyApprove.tableName));
		for (int i = 0; i < ViewProjectWbsQtyApprove.fieldNames.length; i++) {
			model.addObject(ViewProjectWbsQtyApprove.fieldNames[i], language.getText(ViewProjectWbsQtyApprove.fieldNames[i]));
		}
		return model;
	}

	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public ModelAndView approvePost(HttpServletRequest request) throws IOException, ParseException {
			
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
			
		// Read POSTED request variables
		String strProjectId  = request.getParameter("projectId");
		String strCategoryId = request.getParameter("categoryId");
		String strTeamId     = request.getParameter("teamId");
		String strBegda      = request.getParameter("begda");
//		System.out.println("Submitted projectId " + strProjectId + " categoryId " + strCategoryId);
			
		Date begda;
		try {begda = Labels.dmyDF.parse(strBegda);} catch (Exception e) {begda = null;}
		try {
			((ProjectWbsQuantityService)modelService).approve(Integer.parseInt(strProjectId), Integer.parseInt(strCategoryId), Integer.parseInt(strTeamId), begda, null);
		} catch(Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		return new ModelAndView("redirect:/" + getControllerStatic().getRootMapping() + "/approve?projectId="+strProjectId);
	}

}
