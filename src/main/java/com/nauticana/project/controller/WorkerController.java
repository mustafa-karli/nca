package com.nauticana.project.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
import com.nauticana.basis.utils.ControllerStatic;
import com.nauticana.basis.utils.FieldType;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.business.model.Subcontractor;
import com.nauticana.business.service.SubcontractorService;
import com.nauticana.personnel.model.Person;
import com.nauticana.personnel.service.PersonService;
import com.nauticana.project.model.ProjectTeam;
import com.nauticana.project.model.ProjectTeamId;
import com.nauticana.project.model.ProjectTeamPerson;
import com.nauticana.project.model.ProjectTeamPersonId;
import com.nauticana.project.model.Worker;
import com.nauticana.project.service.ManhourJdbcService;
import com.nauticana.project.service.ProjectTeamPersonService;
import com.nauticana.project.service.ProjectTeamService;
import com.nauticana.project.service.WorkerService;

@Controller
@ResponseBody
@RequestMapping("/" + Worker.ROOTMAPPING)
public class WorkerController  extends AbstractController<Worker,Integer> {

	public static final String   module      = "manhour/";
	public static final String   editView    = module + Worker.ROOTMAPPING + "Edit";
	public static final String   selectView  = module + Worker.ROOTMAPPING + "Select";

	@Override
	public ModelAndView editView(ControllerStatic controllerStatic, PortalLanguage language) {
		return new ModelAndView(editView)
				.addObject("controller", controllerStatic)
        		.addObject("language", language);
	}

	@Override
	public ModelAndView selectView(ControllerStatic controllerStatic, PortalLanguage language) {
		return new ModelAndView(selectView)
				.addObject("controller", controllerStatic)
        		.addObject("language", language);
	}

	@Autowired
	private ProjectTeamPersonService projectTeamPersonService;

	@Autowired
	private ProjectTeamService projectTeamService;

	@Autowired
	private SubcontractorService subcontractorService;

	@Autowired
	private PersonService personService;

	@Autowired
	private ManhourJdbcService manhourJdbcService;

	@RequestMapping(value = "/selectPerson")
	public ModelAndView selectPersonnel(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return new ModelAndView("redirect:/unauthorized");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		String parentKey = request.getParameter(Labels.PARENTKEY);

		String operation = request.getParameter("operation");
		String nextpage = request.getParameter(Labels.NEXTPAGE);
		if (Utils.emptyStr(nextpage)) {
			nextpage = "project/show?id="+ parentKey;
		}
		String prevpage = nextpage;
//		System.out.println(" Next/prev page : " + nextpage);
		
		if (Labels.CHOOSE.equals(operation)) {
			String personIds = request.getParameter("personIds");
			if (!Utils.emptyStr(personIds)) {
				String[] p = personIds.split(",");
				ProjectTeam pt = projectTeamService.findById(new ProjectTeamId(parentKey));
				for (int i = 1; i < p.length; i++) {
					int personId = Integer.parseInt(p[i]);
					Worker w = ((WorkerService) modelService).findByPersonId(personId);
					if (w == null) w = ((WorkerService) modelService).getPerson(manhourJdbcService.localPersonnel(getClient(session),personId));
					if (w != null) {
						ProjectTeamPersonId id = new ProjectTeamPersonId(pt.getId().getProjectId(), pt.getId().getTeamId(), w.getId());
						ProjectTeamPerson ptp = projectTeamPersonService.findById(id);
						if (ptp == null) {
							ptp = new ProjectTeamPerson();
							ptp.setId(id);
							ptp.setWorker(w);
							ptp.setProjectTeam(pt);
							ptp.setTeamLead((byte) 0);
							try {
								ptp = projectTeamPersonService.create(ptp);
							} catch(Exception e) {
								return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
							}
						}
					}
				}
			}
			if (Utils.emptyStr(nextpage))
				return new ModelAndView("redirect:list");
			else
				return new ModelAndView("redirect:/" + nextpage);
		}
		ArrayList<String>  fields  = new ArrayList<String>();
		ArrayList<String>  filters = new ArrayList<String>();
		ArrayList<Integer> types  = new ArrayList<Integer>();
		fields.add("OWNER_ID");
		filters.add(getClient(session)+"");
		types.add(FieldType.T_INT);
		String memberType = request.getParameter("memberType");
		String supervisor = request.getParameter("supervisor");
		
		ModelAndView model;
		if ("LEAD".equals(memberType))
			model = new ModelAndView(module + "/leadPersonSelect");
		else
			model = new ModelAndView(module + "/personSelect");

		model.addObject(Labels.PARENTKEY, parentKey);

		FieldType[] fieldTypes = basisService.fieldTypes(personService.tableName());
		for (FieldType fieldType : fieldTypes) {
			String fieldFilter = request.getParameter(fieldType.fieldName);
			if (fieldFilter != null) {
				fieldFilter = fieldFilter.trim();
				if (!Utils.emptyStr(fieldFilter)) {
					fields.add(fieldType.fieldName);
					filters.add(fieldFilter);
					types.add(fieldType.getType());
				}
			}
		}
		
		if (Labels.SEARCH.equals(operation)) {
			List<Person> records;
			try {
				records = personService.search(fields, filters, types);
				model.addObject("records", records);
			} catch (ParseException e) {
			}
		}
		// if add siblings clicked
		if (supervisor != null) {
			model.addObject("supervisor",supervisor);
		}

		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(personService.tableName()));
		model.addObject(Labels.SEARCH, language.getIconText(Labels.SEARCH));
		model.addObject(Labels.CHOOSE, language.getIconText(Labels.CHOOSE));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.NEXTPAGE, nextpage);
		model.addObject(Labels.PREVPAGE, prevpage);
		return model;
	}

	
	
	@RequestMapping(value = "/selectWorker", method = RequestMethod.GET)
	public ModelAndView selectWorkerGet(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());

		ModelAndView model = new ModelAndView(module + "/workerSelect");
		model.addObject("language", language);
		List<Subcontractor> scl = subcontractorService.findAll();
		if (scl == null || scl.isEmpty())
			return errorPage(language, Labels.ERR_RECORDNOTFOUND, "NO SUBCONTRACTORS");
		model.addObject("subcontractors", scl);
		String s = request.getParameter("subcontractorId");
		if (Utils.emptyStr(s))	s = scl.get(0).getId()+"";
		model.addObject("subcontractorId", s);

		List<Worker> workers = ((WorkerService)modelService).findBySubcontractor(subcontractorService.findById(Integer.parseInt(s)));
		model.addObject("workers", workers);
		String postlink = "worker/selectWorker";
		String parentKey = request.getParameter(Labels.PARENTKEY);
		String memberType = request.getParameter("memberType");
		String nextpage = request.getParameter(Labels.NEXTPAGE);
		if (Utils.emptyStr(nextpage)) {
			if (parentKey.split(",").length>1)
				nextpage = "projectTeam/show?id=" + parentKey;
			else
				nextpage = "project/show?id=" + parentKey;
		}
		String prevpage = nextpage;
		
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(modelService.tableName()));
//		model.addObject(Labels.SELECT, language.getIconText(Labels.SELECT));
//		model.addObject(Labels.OK, language.getIconText(Labels.OK));
//		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.POSTLINK	, postlink);
		model.addObject(Labels.NEXTPAGE, nextpage);
		model.addObject(Labels.PREVPAGE, prevpage);
//		model.addObject(Labels.SUBCONTRACTOR, language.getIconText(Labels.SUBCONTRACTOR));
		model.addObject(Labels.PARENTKEY, parentKey);
		model.addObject("memberType", memberType);
//		for (int i = 0; i < Worker.FIELD_NAMES.length; i++)
//			model.addObject(Worker.FIELD_NAMES[i], language.getText(Worker.FIELD_NAMES[i]));
		return model;
	}
	
	@RequestMapping(value = "/selectWorker", method = RequestMethod.POST)
	public ModelAndView selectWorkerPost(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		String memberType = request.getParameter("memberType");
		String parentKey = request.getParameter(Labels.PARENTKEY);
		String nextpage = request.getParameter(Labels.NEXTPAGE);
//		System.out.println("Parentkey : " + parentKey);
//		String operation = request.getParameter("operation");
//		System.out.println("operation : " + operation);
//		System.out.println("nextpage : " + nextpage);
//		System.out.println("memberType : " + memberType);

		String workerIds = request.getParameter("workerIds");
//		System.out.println("WorkerIds : " + workerIds);
		if (!Utils.emptyStr(workerIds)) {
			String[] p = workerIds.split(",");
			if ("LEAD".equals(memberType)) {
				return new ModelAndView("redirect:/projectTeam/new?parentKey=" + parentKey + "&memberType=" + memberType + "&workerId=" + p[1]);
			} else {
				ProjectTeam pt = projectTeamService.findById(new ProjectTeamId(parentKey));
				for (int i = 1; i < p.length; i++) {
					int workerId = Integer.parseInt(p[i]);
					Worker w = ((WorkerService) modelService).findById(workerId);
					ProjectTeamPersonId id = new ProjectTeamPersonId(pt.getId().getProjectId(), pt.getId().getTeamId(),	w.getId());
					ProjectTeamPerson ptp = projectTeamPersonService.findById(id);
					if (ptp == null) {
						ptp = new ProjectTeamPerson();
						ptp.setId(id);
						ptp.setWorker(w);
						ptp.setProjectTeam(pt);
						ptp.setTeamLead((byte) 0);
						try {
							ptp = projectTeamPersonService.create(ptp);
						} catch(Exception e) {
							return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
						}
					}
				}
			}
		}
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:list");
		else
			return new ModelAndView("redirect:/" + nextpage);
	}
}
