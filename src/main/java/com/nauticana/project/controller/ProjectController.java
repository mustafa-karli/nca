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
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.project.model.Project;
import com.nauticana.project.model.ProjectWbsManhour;
import com.nauticana.project.model.ReportProjectWbsStatus;
import com.nauticana.project.service.ManhourJdbcService;

@Controller
@ResponseBody
@RequestMapping("/" + Project.ROOTMAPPING)
public class ProjectController extends AbstractController<Project, Integer> {

	@Autowired
	private ManhourJdbcService manhourJdbcService;
	
	@RequestMapping(value = "/selectOrganization", method = RequestMethod.GET)
	public ModelAndView selectOrganization(HttpServletRequest request) throws IOException {
		ModelAndView model=new ModelAndView("manhour/organizationSelect"); 
		return model;
	}
	
	
	@RequestMapping(value = "/approveFinal", method = RequestMethod.GET)
	public ModelAndView approveFinal(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		String id = request.getParameter("id");
		if (Utils.emptyStr(id))
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, Labels.PROJECT + " : id");
		
		if (!basisService.authorityChk(username, Labels.PROJECT, Labels.APPROVE_FINAL, id))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.PROJECT + "-" + Labels.APPROVE_FINAL + " ON " + id);
		
		Project project = modelService.findById(modelService.strToId(id));
		if (project.getStatus().equals(Labels.APPROVE_FINAL))
			return errorPage(language, Labels.ERR_ALREADY_APPROVED, Labels.PROJECT + " : " + id);
		project.setStatus(Labels.APPROVE_FINAL);
		try {
			modelService.save(project);
		} catch(Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		manhourJdbcService.addProjectApproval(project.getId(), username, Labels.APPROVE_FINAL);
		return new ModelAndView("redirect:/project/show?id="+id);
	}

	@RequestMapping(value = "/approveWbs", method = RequestMethod.GET)
	public ModelAndView approveWbs(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		String id = request.getParameter("id");
		if (Utils.emptyStr(id))
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, Labels.PROJECT + " : id");

		if (!basisService.authorityChk(username, Labels.PROJECT, Labels.APPROVE_WBS, id))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.PROJECT + "-" + Labels.APPROVE_WBS + " ON " + id);
		
		Project project = modelService.findById(modelService.strToId(id));
		if (project.getStatus().equals(Labels.APPROVE_WBS) || project.getStatus().equals(Labels.APPROVE_FINAL))
			return errorPage(language, Labels.ERR_ALREADY_APPROVED, Labels.PROJECT + " : " + id);
		project.setStatus(Labels.APPROVE_WBS);
		try {
			modelService.save(project);
		} catch(Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		manhourJdbcService.addProjectApproval(project.getId(), username, Labels.APPROVE_WBS);
		return new ModelAndView("redirect:/project/show?id="+id);
	}

	@RequestMapping(value = "/withdrawApprove", method = RequestMethod.GET)
	public ModelAndView withdrawApprove(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		String id = request.getParameter("id");
		if (Utils.emptyStr(id))
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, Labels.PROJECT + " : id");
		
		if (!basisService.authorityChk(username, Labels.PROJECT, Labels.APPROVE_WITHDRAW, id))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.PROJECT + "-" + Labels.APPROVE_WITHDRAW + " ON " + id);
		
		Project project = modelService.findById(modelService.strToId(id));
		project.setStatus(Labels.INITIAL);
		try {
			modelService.save(project);
		} catch(Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		manhourJdbcService.addProjectApproval(project.getId(), username, Labels.INITIAL);
		return new ModelAndView("redirect:/project/show?id="+id);
	}

	@RequestMapping(value = "/approveProgress", method = RequestMethod.GET)
	public ModelAndView approveProgressGet(HttpServletRequest request) throws IOException {
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

		List<String> ids = basisService.authorizedIds(username, Labels.PROJECT, Labels.APPROVE_PROGRESS);
		List<Project> projects = modelService.findByIds(ids);
//		System.out.println("projects : " + projects.size() + " projects");
		if (projects.size() == 0)
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.PROJECT + "-" + Labels.APPROVE_PROGRESS);
		int projectId,year,month;
		String strProjectId = request.getParameter("projectId");
		String strYear  = request.getParameter("year");
		String strMonth = request.getParameter("month");
//		System.out.println("Get approveProgress projectId " + strProjectId + " year " + strYear + " month " + strMonth);
		Calendar c = Calendar.getInstance();
		try {projectId = Integer.parseInt(strProjectId);} catch (Exception e) {projectId = -1;}//projects.get(0).getId();}
		try {year = Integer.parseInt(strYear);} catch (Exception e) {year = c.get(Calendar.YEAR);}
		try {month = Integer.parseInt(strMonth);} catch (Exception e) {month = c.get(Calendar.MONTH)+1;}
	    c.set(year, month-1, 1, 0, 0, 0);
	    Date begda = c.getTime();
	    c.set(year, month-1, c.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date endda = c.getTime();
		
		Date minInitialDate = manhourJdbcService.getMinInitialDate(projectId, begda, endda);
		Date minApprovedDate = manhourJdbcService.getMinApprovedDate(projectId, begda, endda);

//		System.out.println(" Begda " + begda.toString() + " endda " + endda.toString());

		List<ReportProjectWbsStatus> records = manhourJdbcService.getReportProjectWbsStatusAll(projectId, begda, endda, language.code);
		ModelAndView model = new ModelAndView("manhour/projectProgressApprove");
		model.addObject("language", language);
		if (minInitialDate != null)
			model.addObject("minInitialDate", minInitialDate);
		if (minApprovedDate != null)
			model.addObject("minApprovedDate", minApprovedDate);
		model.addObject("projects", projects);
		model.addObject("projectId", projectId);
		model.addObject("records", records);
		model.addObject("year", year);
		model.addObject("month", month);
		model.addObject("MONTHS", dataCache.getDomainOptions("MONTH", language));
		model.addObject(Labels.PAGETITLE, language.getText(ProjectWbsManhour.TABLE_NAME));
		model.addObject(Labels.dataTableNames[0], Labels.dataTableSettings[0]);
		return model;
	}

	@RequestMapping(value = "/approveProgress", method = RequestMethod.POST)
	public ModelAndView approveProgressPost(HttpServletRequest request) throws IOException, ParseException {
			
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
			
		// Read POSTED request variables
		int projectId,year,month;
		String strProjectId = request.getParameter("projectId");
		String strYear  = request.getParameter("year");
		String strMonth = request.getParameter("month");
		String withDraw = request.getParameter("withdraw");
		
//		System.out.println("Submitted projectId " + strProjectId + " year " + strYear + " month " + strMonth);
		try {projectId  = Integer.parseInt(strProjectId);}  catch (Exception e) {return errorPage(language, Labels.ERR_PARAMETER_MISSING, "projectId");}
		try {year  = Integer.parseInt(strYear);}  catch (Exception e) {return errorPage(language, Labels.ERR_PARAMETER_MISSING, "year");}
		try {month = Integer.parseInt(strMonth);} catch (Exception e) {return errorPage(language, Labels.ERR_PARAMETER_MISSING, "month");}
		Calendar c = Calendar.getInstance();
	    c.set(year, month-1, 1, 0, 0, 0);
	    Date begda = c.getTime();
	    c.set(year, month-1, c.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date endda = c.getTime();

//		Project p = MODEL_SERVICES.findById(projectId);
		if ("YES".equals(withDraw)) {
			manhourJdbcService.setProjectWbsQtyStatus(projectId, begda, endda, Labels.INITIAL);
			manhourJdbcService.setProjectWbsMhStatus(projectId, begda, endda, Labels.INITIAL);
//			for (ProjectWbs pw : p.getProjectWbses()) {
//				for (ProjectTeam pt : p.getProjectTeams()) {
//					pwms.withdrawApprove(projectId, pw.getId().getCategoryId(), pt.getId().getTeamId(), begda, endda);
//					pwqs.withdrawApprove(projectId, pw.getId().getCategoryId(), pt.getId().getTeamId(), begda, endda);
//				}
//			}
		} else {
			manhourJdbcService.setProjectWbsQtyStatus(projectId, begda, endda, Labels.APPROVED);
			manhourJdbcService.setProjectWbsMhStatus(projectId, begda, endda, Labels.APPROVED);
//			for (ProjectWbs pw : p.getProjectWbses()) {
//				for (ProjectTeam pt : p.getProjectTeams()) {
//					pwms.approve(projectId, pw.getId().getCategoryId(), pt.getId().getTeamId(), begda, endda);
//					pwqs.approve(projectId, pw.getId().getCategoryId(), pt.getId().getTeamId(), begda, endda);
//				}
//			}
		}
		return new ModelAndView("redirect:/" + getControllerStatic().getRootMapping() + "/approveProgress?projectId="+strProjectId+"&month="+strMonth+"&year="+strYear);
	}
}
