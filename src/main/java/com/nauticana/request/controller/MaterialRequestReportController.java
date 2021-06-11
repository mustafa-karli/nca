package com.nauticana.request.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.personnel.model.Organization;
import com.nauticana.personnel.model.ViewOrganizationContainer;
import com.nauticana.personnel.service.OrganizationService;
import com.nauticana.request.model.MaterialRequest;
import com.nauticana.request.model.MaterialRequestItem;
import com.nauticana.request.model.ViewMaterialRequestTotal;
import com.nauticana.request.service.MaterialRequestService;

@Controller
@RequestMapping("/materialRequestReport")
public class MaterialRequestReportController extends AbstractReportController {

	@Autowired
	OrganizationService				organizationService;

	@Autowired
	MaterialRequestService			materialRequestService;

	public static final String		module		= "request/";
	public static final String		listView	= module + "reportList";

	public static final String[]	reportNames	= new String[] { "MATERIAL_REQUEST_BY_ORGANIZATION",                      "MATERIAL_REQUEST_BY_PRODUCT",                      "MATERIAL_REQUEST_BY_DEFINE" };
	public static final String[]	reportMaps	= new String[] { "materialRequestReport/materialRequestByOrganization",   "materialRequestReport/materialRequestByProduct",   "materialRequestReport/materialRequestByDefine" };
	public static final String[]	reportView	= new String[] { module + "materialRequestByOrganization",                 module + "materialRequestByProduct",               module + "materialRequestByDefine" };

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listGet(HttpServletRequest request) throws IOException {

		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

//		List<String> orgIds = basisService.authorizedIds(username, Labels.ORGANIZATION, Labels.SHIPMENT);
//		if (orgIds.isEmpty())
//			return errorPage(language, Labels.ERR_UNAUTHORIZED, "No organizations authorized for shipment reporting");

		String strYear = request.getParameter("year");
		String strMonth = request.getParameter("month");
		String strBegda = request.getParameter("begda");
		String strEndda = request.getParameter("endda");

		int year, month;
		Date begda, endda;
		Calendar c = Calendar.getInstance();
		try {
			year = Integer.parseInt(strYear);
		} catch (Exception e) {
			year = c.get(Calendar.YEAR);
		}
		try {
			month = Integer.parseInt(strMonth);
		} catch (Exception e) {
			month = c.get(Calendar.MONTH) + 1;
		}
		try {
			begda = Labels.dmyDF.parse(strBegda);
		} catch (Exception e) {
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
			begda = c.getTime();
		}
		try {
			endda = Labels.dmyDF.parse(strEndda);
		} catch (Exception e) {
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
			endda = c.getTime();
		}

		
		ModelAndView model = new ModelAndView(listView);
		model.addObject("year", year);
		model.addObject("month", month);
		model.addObject("MONTHS", dataCache.getDomainOptions("MONTH", language));
		model.addObject(Labels.PAGETITLE, language.getText(Labels.REPORTS));

		Map<String, String> reports = new LinkedHashMap<String, String>();

		for (int i = 0; i < reportMaps.length; i++) {
			reports.put(language.getText(reportNames[i]), reportMaps[i]);
		}
		model.addObject("reports", reports);
		model.addObject(Labels.BEGDA, language.getText(Labels.BEGDA));
		model.addObject("begda", Labels.ymdDF.format(begda));
		model.addObject(Labels.ENDDA, language.getText(Labels.ENDDA));
		model.addObject("endda", Labels.ymdDF.format(endda));

		return model;
	}

	@RequestMapping(value = "/materialRequestByOrganization", method = RequestMethod.POST)
	public ModelAndView report0(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");
		int client = getClient(session);
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		int organizationId = (Integer) session.getAttribute("ORGANIZATION_ID");
		String strBegda = request.getParameter("begda");
		String strEndda = request.getParameter("endda");
		Date begda;
		try {
			begda = Labels.ymdDF.parse(strBegda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "BEGDA not selected");
		}

		Date endda;
		try {
			endda = Labels.fullYMDdf.parse(strEndda + " 23:59:59");
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "ENDDA not selected");
		}

		Organization o = organizationService.findById(organizationId);
		ViewOrganizationContainer topOrganization = new ViewOrganizationContainer(o.getId(), o.getCaption());
		organizationService.getSiblings(topOrganization);
		List<Integer> orgIds = new ArrayList<Integer>();
		organizationService.findAllChildren(topOrganization, orgIds);
		HashMap<Integer, ViewOrganizationContainer> orgMap = new HashMap<Integer, ViewOrganizationContainer>();
		organizationService.findAllChildren(topOrganization, orgMap);
//		List<Integer> orgIds = organizationService.findAllChildren(organizationId);
		List<MaterialRequest> records = materialRequestService.findByOrganization(client, orgIds, begda, endda);
		for (MaterialRequest record : records) {
			ViewOrganizationContainer org = orgMap.get(record.getOrganization().getId());
			List<Object> items=org.getItems();
			if (items == null) {
				items = new ArrayList<Object>();
				org.setItems(items);
			}
			items.add(record);
		}
		ModelAndView model = new ModelAndView(reportView[0]);
		model.addObject("topOrganization", topOrganization);
		model.addObject("language", language);
		return model;
	}

	@RequestMapping(value = "/materialRequestByProduct", method = RequestMethod.POST)
	public ModelAndView report1(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");
		int client = getClient(session);
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		String strBegda = request.getParameter("begda");
		String strEndda = request.getParameter("endda");
		Date begda;
		try {
			begda = Labels.ymdDF.parse(strBegda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "BEGDA not selected");
		}

		Date endda;
		try {
			endda = Labels.fullYMDdf.parse(strEndda + " 23:59:59");
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "ENDDA not selected");
		}

		List<MaterialRequest> records = materialRequestService.findAllActive(client, begda, endda);
		HashMap<Integer, ViewMaterialRequestTotal> totals = new HashMap<Integer, ViewMaterialRequestTotal>();

		for (MaterialRequest materialRequest : records) {
			for (MaterialRequestItem item : materialRequest.getMaterialRequestItems()) {
				ViewMaterialRequestTotal x = totals.get(item.getMaterial().getId());
				if (x == null) {
					x = new ViewMaterialRequestTotal(item.getMaterial().getId(), item.getMaterial().getCaption(), "MATERIAL_REQUEST", item.getQuantity().floatValue(), item.getUnit());
					x.setOrderLines(item.getId().getMaterialRequestId() + "-" + item.getId().getLine());
					totals.put(item.getMaterial().getId(), x);
				} else {
					x.setQuantity(x.getQuantity() + item.getQuantity().floatValue());
					x.setOrderLines(x.getOrderLines() + "," + item.getId().getMaterialRequestId() + "-" + item.getId().getLine());
				}
			}

		}

		ModelAndView model = new ModelAndView(reportView[1]);
		model.addObject("records", records);
		model.addObject("language", language);
		model.addObject("totals", totals.values());

		return model;
	}

	@RequestMapping(value = "/materialRequestByDefine", method = RequestMethod.POST)
	public ModelAndView report2(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");
		int client = getClient(session);
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		String strBegda = request.getParameter("begda");
		String strEndda = request.getParameter("endda");
		Date begda;
		try {
			begda = Labels.ymdDF.parse(strBegda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "BEGDA not selected");
		}

		Date endda;
		try {
			endda = Labels.fullYMDdf.parse(strEndda + " 23:59:59");
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "ENDDA not selected");
		}

		List<MaterialRequest> records = materialRequestService.findByPurpose(client, "D", begda, endda);
		ModelAndView model = new ModelAndView(reportView[2]);
		model.addObject("records", records);
		model.addObject("language", language);
		return model;
	}

	@RequestMapping(value = "/materialRequestAll", method = RequestMethod.POST)
	public ModelAndView report4(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");
		int client = getClient(session);
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		int organizationId = (Integer) session.getAttribute("ORGANIZATION_ID");
		String strBegda = request.getParameter("begda");
		String strEndda = request.getParameter("endda");
		Date begda;
		try {
			begda = Labels.ymdDF.parse(strBegda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "BEGDA not selected");
		}

		Date endda;
		try {
			endda = Labels.fullYMDdf.parse(strEndda + " 23:59:59");
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "ENDDA not selected");
		}

		List<Integer> organizations = organizationService.findAllChildren(organizationId);
		List<MaterialRequest> records = materialRequestService.findByOrganization(client, organizations, begda, endda);
		HashMap<Integer, ViewMaterialRequestTotal> totals = new HashMap<Integer, ViewMaterialRequestTotal>();

		for (MaterialRequest materialRequest : records) {
			for (MaterialRequestItem item : materialRequest.getMaterialRequestItems()) {
				ViewMaterialRequestTotal x = totals.get(item.getMaterial().getId());
				if (x == null) {
					x = new ViewMaterialRequestTotal(item.getMaterial().getId(), item.getMaterial().getCaption(), "MATERIAL_REQUEST", item.getQuantity().floatValue(), item.getUnit());
					x.setOrderLines(item.getId().getMaterialRequestId() + "-" + item.getId().getLine());
					totals.put(item.getMaterial().getId(), x);
				} else {
					x.setQuantity(x.getQuantity() + item.getQuantity().floatValue());
					x.setOrderLines(x.getOrderLines() + "," + item.getId().getMaterialRequestId() + "-" + item.getId().getLine());
				}
			}

		}

		ModelAndView model = new ModelAndView(reportView[4]);
		model.addObject("records", records);
		model.addObject("language", language);
		model.addObject("totals", totals.values());

		return model;
	}

}
