package com.nauticana.project.controller;

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
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.project.model.ProjectTeam;
import com.nauticana.project.model.ProjectTeamId;
import com.nauticana.project.model.ProjectTeamPerson;
import com.nauticana.project.model.ProjectTeamPersonId;
import com.nauticana.project.model.Worker;
import com.nauticana.project.service.ManhourJdbcService;
import com.nauticana.project.service.ProjectTeamPersonService;
import com.nauticana.project.service.WorkerService;

@Controller
@ResponseBody
@RequestMapping("/" + ProjectTeam.ROOTMAPPING)
public class ProjectTeamController extends AbstractController<ProjectTeam, ProjectTeamId>{

	public static final String     module        = "manhour/";
//	public static final String     editView      = module + ProjectTeam.ROOTMAPPING + "Edit";
//	public static final String     showView      = module + ProjectTeam.ROOTMAPPING + "Show";

//	@Override
//	public String editView() {return editView;}

//	@Override
//	public String showView() {return showView;}

	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private ManhourJdbcService manhourJdbcService;
	
	@Autowired
	private ProjectTeamPersonService projectTeamPersonService;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newGet(HttpServletRequest request) {
		ModelAndView model = super.newGet(request);
		HttpSession session = request.getSession(true);
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
//		String parentKey = request.getParameter(Labels.PARENTKEY);
//		String prevpage = prevPage(parentKey);
		model.addObject("SELECT_EMPLOYEE", language.getIconText("SELECT_EMPLOYEE"));
		model.addObject("SELECT_SUBCONTRACTOR", language.getIconText("SELECT_SUBCONTRACTOR"));
		model.addObject("ADD_SIBLINGS", language.getIconText("ADD_SIBLINGS"));
//		model.addObject(Labels.PREVPAGE, prevpage);

		Worker w = null;
		String strPersonId = request.getParameter("personId");
		if (!Utils.emptyStr(strPersonId)) {
			int personId = Integer.parseInt(strPersonId);
			w = workerService.findByPersonId(personId);
			if (w == null) w = workerService.getPerson(manhourJdbcService.localPersonnel(getClient(session), personId));
			model.addObject("personId", personId);
		}
		if (w == null) {
			String strWorkerId = request.getParameter("workerId");
			if (!Utils.emptyStr(strWorkerId)) {
				w = workerService.findById(Integer.parseInt(strWorkerId));
			}
		}
		if (w != null) {
			model.addObject("workerId", w.getId());
			model.addObject("workerCaption", w.getCaption());
		}
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView newPost(@ModelAttribute ProjectTeam record, BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		String nextpage = request.getParameter(Labels.NEXTPAGE);

		if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		if (result.hasErrors()) {
			String msgText = "";
			for (ObjectError e : result.getAllErrors()) {
				msgText = msgText + "\n" + e.toString();
			}
			return errorPage(language, Labels.ERR_BINDING, msgText);
		}

		String strWorkerId = request.getParameter("workerId");
		if (!Utils.emptyStr(strWorkerId)) {
			Worker w = workerService.findById(Integer.parseInt(strWorkerId));
			if (w == null)
				return errorPage(language, Labels.ERR_RECORDNOTFOUND, " Worker Id : " + strWorkerId);
			try {
				ProjectTeam pt = modelService.create(record);
				ProjectTeamPersonId ptpId = new ProjectTeamPersonId(pt.getId().getProjectId(), pt.getId().getTeamId(), w.getId());
				ProjectTeamPerson ptp = new ProjectTeamPerson();
				ptp.setId(ptpId);
				ptp.setWorker(w);
				ptp.setProjectTeam(pt);
				ptp.setTeamLead((byte) 1);
				projectTeamPersonService.create(ptp);
				//if addSublings selected, team leads personnel added to team
//				if ("1".equals(request.getParameter("addSublings")) && w.getPersonId() > 0) {
//					ArrayList<String> filter = new ArrayList<String>();
//					filter.add("SUPERVISOR" + "," + w.getPersonId());
//					List<Person> siblings = manhourJdbcService.localPersonnel(filter);
//					for (Person lp : siblings) {
//						Worker sbl = workerService.findByPersonId(lp.getPersonId());
//						if (sbl == null) sbl = workerService.getLocalPerson(lp);
//						if (sbl != null) {
//							ProjectTeamPersonId memberId = new ProjectTeamPersonId(pt.getId().getProjectId(), pt.getId().getTeamId(), sbl.getId());
//							ProjectTeamPerson teamMember = projectTeamPersonService.findById(memberId);
//							if (teamMember == null) {
//								teamMember = new ProjectTeamPerson(memberId, sbl, pt, (byte) 0);
//								teamMember = projectTeamPersonService.create(teamMember);
//							}
//						}
//					}
//				}
				nextpage = "projectTeam/show?id=" + pt.getId().toString();
			} catch(Exception e) {
				return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
			}

		}

		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:list");
		else
			return new ModelAndView("redirect:/" + nextpage);
	}
}
