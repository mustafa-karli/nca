package com.nauticana.project.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractReportController;
import com.nauticana.basis.service.BasisService;
import com.nauticana.basis.utils.DataCache;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.project.model.Project;
import com.nauticana.project.model.ReportProjectWbsStatus;
import com.nauticana.project.model.ViewProgressBrief;
import com.nauticana.project.service.ManhourJdbcService;
import com.nauticana.project.service.ProjectService;

@Controller
@RequestMapping("/projectReport")
public class ProjectReportController extends AbstractReportController {

	@Autowired
	protected DataCache dataCache;

	@Autowired
	protected BasisService basisService;

	@Autowired
	private ManhourJdbcService manhourJdbcService;

	@Autowired
	protected ProjectService projectService;

//	@Autowired
//	protected ProjectTeamService teamService;
//
//	@Autowired
//	protected SubcontractorService subcontractorService;

	public static final String module   = "manhour/";
	public static final String listView = module + "reportList";
	
	public static final String[] reportNames = new String[] {
			"REPORT_CUMULATIVE_DIRECT_MANHOUR",
			"REPORT_TEAM_MANHOUR",
			"REPORT_SUBCONTRACTOR_MANHOUR",
			"REPORT_BRIEF_MANHOUR"};
//			"REPORT_CITIZEN_TYPE"};
	
	public static final String[] reportMaps = new String[] {
			"projectReport/cumulativeMh",
			"projectReport/teamMh",
			"projectReport/subcontractorMh",
			"projectReport/briefMh"};

	public ModelAndView errorPage(PortalLanguage language, String ERRCODE, String msgText) {
		ModelAndView model = new ModelAndView("basis/errorPage");
		model.addObject("ERRCODE", language.getText(ERRCODE));
		model.addObject("MSGTEXT", msgText);
		return model;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listGet(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		List<String> ids = basisService.authorizedIds(username, Labels.PROJECT_TEAM, Labels.APPROVE_MANHOUR);
		List<Project> projects = projectService.findByIds(ids);
		if (projects.isEmpty())
			return errorPage(language, Labels.ERR_UNAUTHORIZED, "No projects authorized for reporting");
		
		String strProjectId = request.getParameter("projectId");
		String strYear  = request.getParameter("year");
		String strMonth = request.getParameter("month");
		String strBegda = request.getParameter("begda");
		String strEndda = request.getParameter("endda");

		int projectId,year,month;
		Date begda, endda;
		Calendar c = Calendar.getInstance();
		try {projectId = Integer.parseInt(strProjectId);} catch (Exception e) {projectId = projects.get(0).getId();}
		try {year = Integer.parseInt(strYear);} catch (Exception e) {year = c.get(Calendar.YEAR);}
		try {month = Integer.parseInt(strMonth);} catch (Exception e) {month = c.get(Calendar.MONTH)+1;}
		try {begda = Labels.dmyDF.parse(strBegda);} catch (Exception e) {
		    c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		    begda = c.getTime();
		}
		try {endda = Labels.dmyDF.parse(strEndda);} catch (Exception e) {
		    c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.getActualMaximum(Calendar.DAY_OF_MONTH));
		    endda = c.getTime();
		}

		ModelAndView model = new ModelAndView(listView);
		model.addObject("projects", projects);
		model.addObject("projectId", projectId);
		model.addObject("year", year);
		model.addObject("month", month);
		model.addObject("MONTHS_LIST", dataCache.getDomainOptions("MONTH", language));
		model.addObject(Labels.PROJECT, language.getText(Labels.PROJECT));
		model.addObject(Labels.PAGETITLE, language.getText(Labels.REPORTS));

		Map<String, String> reports = new LinkedHashMap<String, String>();
		
		for (int i = 0; i < reportMaps.length; i++) {
			reports.put(language.getText(reportNames[i]), reportMaps[i]);
		}
		model.addObject("reports", reports);
		model.addObject(Labels.BEGDA, language.getText(Labels.BEGDA));
	    model.addObject("begda", Labels.dmyDF.format(begda));
		model.addObject(Labels.ENDDA, language.getText(Labels.ENDDA));
	    model.addObject("endda", Labels.dmyDF.format(endda));
	    model.addObject(Labels.PERIOD, language.getText(Labels.PERIOD));
	    
	    return model;
	}
	
	@RequestMapping(value = "/cumulativeMh", method = RequestMethod.POST)
	public ModelAndView report1(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

//		String nextpage = request.getParameter(Labels.NEXTPAGE);
		String strProjectId = request.getParameter("projectId");
		String strBegda = request.getParameter("begda");
		String strEndda = request.getParameter("endda");

//		System.out.println("submitted report/cumulativeMh projectId " + strProjectId + " begda : " + strBegda + " endda : " + strEndda + " Nextpage : " + nextpage);

		int projectId;
		if (!Utils.emptyStr(strProjectId))
			projectId = Integer.parseInt(strProjectId);
		else
			return errorPage(language, Labels.ERR_WRONGDATA, "No project selected");
		Project project = projectService.findById(projectId);

		Date begda;
		try {
			begda = Labels.dmyDF.parse(strBegda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "BEGDA not selected");
		}

		Date endda;
		try {
			endda = Labels.dmyDF.parse(strEndda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "ENDDA not selected");
		}

		String prevPage= 	"report/list?projectId=" + strProjectId + "&begda=" + strBegda + "&endda=" + strEndda;

		List<ReportProjectWbsStatus> records = manhourJdbcService.getReportProjectWbsStatusApproved(projectId, begda, endda, language.code);
		Date unapprovedDate = manhourJdbcService.getMinInitialDate(projectId, begda, endda);
	
		ModelAndView model = new ModelAndView(module + "reportCumulativeMh");
		if (unapprovedDate != null)
			model.addObject("unapprovedDate", unapprovedDate);
		model.addObject("project", project);
		model.addObject("team", project);
		model.addObject("projectId", projectId);
		model.addObject("records", records);
		model.addObject("begda", Labels.dmyDF.format(begda));
		model.addObject("endda", Labels.dmyDF.format(endda));
		model.addObject("term", Labels.dmyDF.format(begda)+" .. "+Labels.dmyDF.format(endda));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PROJECT, language.getText(Labels.PROJECT));
		model.addObject(Labels.REALISED, language.getIconText(Labels.REALISED));
		model.addObject(Labels.TOTAL, language.getIconText(Labels.TOTAL));
		model.addObject(Labels.PREVPAGE, prevPage);

		model.addObject(Labels.PROJECT, language.getText(Labels.PROJECT));
		model.addObject(Labels.PAGETITLE, language.getText(Labels.REPORTS));
		model.addObject(Labels.dataTableNames[2], Labels.dataTableSettings[2]);
		
		Map<String, String> reports = new LinkedHashMap<String, String>();
		
		for (int i = 0; i < reportMaps.length; i++) {
			reports.put(language.getText(reportNames[i]), reportMaps[i]);
		}
		model.addObject("reports", reports);
//		model.addObject(Labels.BEGDA, language.getText(Labels.BEGDA));
//	    model.addObject("begda", Labels.ymdDF.format(begda));
//		model.addObject(Labels.ENDDA, language.getText(Labels.ENDDA));
//	    model.addObject("endda", Labels.ymdDF.format(endda));
	    model.addObject(Labels.PROJECT_TEAM, language.getText(Labels.PROJECT_TEAM));
	    model.addObject(Labels.SUBCONTRACTOR, language.getText(Labels.SUBCONTRACTOR));
		
		model.addObject(Labels.PAGETITLE, language.getText(reportNames[0]));
		for (int i = 0; i < ReportProjectWbsStatus.fieldNames.length; i++) {
			model.addObject(ReportProjectWbsStatus.fieldNames[i], language.getText(ReportProjectWbsStatus.fieldNames[i]));
		}
		return model;
	}
	
	@RequestMapping(value = "/teamMh", method = RequestMethod.POST)
	public ModelAndView report2(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

//		String nextpage = request.getParameter(Labels.NEXTPAGE);
		String strProjectId = request.getParameter("projectId");
		String strBegda = request.getParameter("begda");
		String strEndda = request.getParameter("endda");
		String strTeamId = request.getParameter("teamId");

//		System.out.println("submitted report/teamMh projectId " + strProjectId + " begda : " + strBegda + " endda : " + strEndda + " teamId : " + strTeamId + " Nextpage : " + nextpage);

		int projectId;
		if (!Utils.emptyStr(strProjectId))
			projectId = Integer.parseInt(strProjectId);
		else
			return errorPage(language, Labels.ERR_WRONGDATA, "No project selected");
		Project project = projectService.findById(projectId);

		Date begda;
		try {
			begda = Labels.dmyDF.parse(strBegda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "BEGDA not selected");
		}

		Date endda;
		try {
			endda = Labels.dmyDF.parse(strEndda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "ENDDA not selected");
		}
		
		List<ReportProjectWbsStatus>records = null;
//		int teamId;
		if (!Utils.emptyStr(strTeamId)) {
//			teamId = Integer.parseInt(strTeamId);
			records = manhourJdbcService.getReportProjectWbsStatusForTeam(projectId,Integer.parseInt(strTeamId), begda, endda, language.code);
		}
//		else
//			return errorPage(language, Labels.ERR_WRONGDATA, "No team selected");
		
		String prevPage= 	"report/list?projectId=" + strProjectId + "&teamId=" + strTeamId + "&begda=" + strBegda + "&endda=" + strEndda;
		
//		ProjectTeam team = teamService.findById(new ProjectTeamId(projectId,teamId));

		Date unapprovedDate = manhourJdbcService.getMinInitialDate(projectId, begda, endda);

		ModelAndView model = new ModelAndView(module + "reportCumulativeTeamMh");

		model.addObject("project", project);
		if (unapprovedDate != null)
			model.addObject("unapprovedDate", unapprovedDate);
		model.addObject("projectId", projectId);
		model.addObject("records", records);
		model.addObject("teamId", strTeamId);
		model.addObject("begda", Labels.dmyDF.format(begda));
		model.addObject("endda", Labels.dmyDF.format(endda));
		model.addObject("term", Labels.dmyDF.format(begda)+" .. "+Labels.dmyDF.format(endda));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PROJECT, language.getText(Labels.PROJECT));
		model.addObject(Labels.PROJECT_TEAM, language.getText(Labels.PROJECT_TEAM));
		model.addObject(Labels.SUBCONTRACTOR, language.getText(Labels.SUBCONTRACTOR));
		model.addObject(Labels.REALISED, language.getIconText(Labels.REALISED));
		model.addObject(Labels.TOTAL, language.getIconText(Labels.TOTAL));
		model.addObject(Labels.PREVPAGE, prevPage);
		model.addObject("ISTEAM", "X");

		model.addObject(Labels.PAGETITLE, language.getText(reportNames[1]));
		for (int i = 0; i < ReportProjectWbsStatus.fieldNames.length; i++) {
			model.addObject(ReportProjectWbsStatus.fieldNames[i], language.getText(ReportProjectWbsStatus.fieldNames[i]));
		}
		return model;

	}
	
	@RequestMapping(value = "/subcontractorMh", method = RequestMethod.POST)
	public ModelAndView report6(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

//		String nextpage = request.getParameter(Labels.NEXTPAGE);
		String strProjectId = request.getParameter("projectId");
		String strBegda = request.getParameter("begda");
		String strEndda = request.getParameter("endda");
		String strSubcontractorId = request.getParameter("subcontractorId");

//		System.out.println("submitted report/teamMh projectId " + strProjectId + " begda : " + strBegda + " endda : " + strEndda + " subcontractorId : " + strSubcontractorId + " Nextpage : " + nextpage);

		int projectId;
		if (!Utils.emptyStr(strProjectId))
			projectId = Integer.parseInt(strProjectId);
		else
			return errorPage(language, Labels.ERR_WRONGDATA, "No project selected");
		Project project = projectService.findById(projectId);

		Date begda;
		try {
			begda = Labels.dmyDF.parse(strBegda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "BEGDA not selected");
		}

		Date endda;
		try {
			endda = Labels.dmyDF.parse(strEndda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "ENDDA not selected");
		}
		
		List<ReportProjectWbsStatus>records = null;
//		int subcontractorId;
		if (!Utils.emptyStr(strSubcontractorId))
//			subcontractorId = Integer.parseInt(strSubcontractorId);
			records = manhourJdbcService.getReportProjectWbsStatusForSubcontractor(projectId,Integer.parseInt(strSubcontractorId), begda, endda, language.code);
//		else
//			return errorPage(language, Labels.ERR_WRONGDATA, "No subcontractor selected");
		
		String prevPage= 	"report/list?projectId=" + strProjectId + "&subcontractorId=" + strSubcontractorId + "&begda=" + strBegda + "&endda=" + strEndda;

//		Subcontractor subcontractor = subcontractorService.findById(subcontractorId);
//		List<ReportProjectWbsStatus>records = manhourJdbcService.getReportProjectWbsStatusForSubcontractor(projectId,subcontractorId, begda, endda);

		Date unapprovedDate = manhourJdbcService.getMinInitialDate(projectId, begda, endda);

		ModelAndView model = new ModelAndView(module + "reportCumulativeTeamMh");

		model.addObject("project", project);
		if (unapprovedDate != null)
			model.addObject("unapprovedDate", unapprovedDate);
		model.addObject("projectId", projectId);
		model.addObject("records", records);
		model.addObject("subcontractorId", strSubcontractorId);
		model.addObject("begda", Labels.dmyDF.format(begda));
		model.addObject("endda", Labels.dmyDF.format(endda));
		model.addObject("term", Labels.dmyDF.format(begda)+"-"+Labels.dmyDF.format(endda));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PROJECT, language.getText(Labels.PROJECT));
		model.addObject(Labels.PROJECT_TEAM, language.getText(Labels.PROJECT_TEAM));
		model.addObject(Labels.SUBCONTRACTOR, language.getText(Labels.SUBCONTRACTOR));
		model.addObject(Labels.REALISED, language.getIconText(Labels.REALISED));
		model.addObject(Labels.TOTAL, language.getIconText(Labels.TOTAL));
		model.addObject(Labels.PREVPAGE, prevPage);

		
		model.addObject(Labels.PAGETITLE, language.getText(reportNames[2]));
		for (int i = 0; i < ReportProjectWbsStatus.fieldNames.length; i++) {
			model.addObject(ReportProjectWbsStatus.fieldNames[i], language.getText(ReportProjectWbsStatus.fieldNames[i]));
		}
		return model;

	}
	
	@RequestMapping(value = "/briefMh", method = RequestMethod.POST)
	public ModelAndView report3(HttpServletRequest request) throws IOException {
	// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

//		String nextpage = request.getParameter(Labels.NEXTPAGE);
//		System.out.println("Nextpage : " + nextpage);

		int projectId;
		String strProjectId = request.getParameter("projectId");
		if (!Utils.emptyStr(strProjectId))
			projectId = Integer.parseInt(strProjectId);
		else
			return errorPage(language, Labels.ERR_WRONGDATA, "No project selected");
		Project project = projectService.findById(projectId);

		String prevPage= 	"report/list?projectId=" + strProjectId;

//		List<ViewWbsQuantity> records = manhourJdbcService.aggrProjectWbsQuantity(projectId);
//		List<ViewWbsQuantity> records = manhourJdbcService.aggrProjectWbsQuantityTree(projectId);
		List<ViewProgressBrief> records = manhourJdbcService.getProjectBrief(projectId, language.code);

		ModelAndView model = new ModelAndView(module + "reportBriefMh");
		
		model.addObject("project", project);
		model.addObject("projectId", projectId);
		model.addObject("records", records);
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PROJECT, language.getText(Labels.PROJECT));
		model.addObject(Labels.PROJECT_EXECUTION_PLAN, language.getText(Labels.PROJECT_EXECUTION_PLAN));
		model.addObject(Labels.TENDER, language.getText(Labels.TENDER));
		model.addObject(Labels.PLANNED, language.getText(Labels.PLANNED));
		model.addObject(Labels.REALISED, language.getText(Labels.REALISED));
		model.addObject(Labels.PREVPAGE, prevPage);
		model.addObject(Labels.MANHOUR, language.getText(Labels.MANHOUR));
		model.addObject(Labels.dataTableNames[0], Labels.dataTableSettings[0]);
	
		model.addObject(Labels.PAGETITLE, language.getText(reportNames[3]));
		for (int i = 0; i < ViewProgressBrief.fieldNames.length; i++) {
			model.addObject(ViewProgressBrief.fieldNames[i], language.getText(ViewProgressBrief.fieldNames[i]));
		}

		return model;
	}

	@RequestMapping(value = "/citizenType", method = RequestMethod.GET)
	public ModelAndView report5(HttpServletRequest request) throws IOException {
		ModelAndView model = new ModelAndView("citizenType");
		model.addObject(Labels.PAGETITLE, reportNames[4]);

		
		return model;
	}

}
