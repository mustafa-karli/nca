package com.nauticana.shipment.controller;

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
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.personnel.service.OrganizationService;
import com.nauticana.request.service.MaterialRequestService;
import com.nauticana.shipment.model.ViewShipmentLineStatus;
import com.nauticana.shipment.service.ShipmentJdbcService;

@Controller
@RequestMapping("/shipmentReport")
public class ShipmentReportController extends AbstractReportController {

	@Autowired
	private ShipmentJdbcService		shipmentJdbcService;

	@Autowired
	OrganizationService				organizationService;

	@Autowired
	MaterialRequestService			materialRequestService;

	public static final String		module		= "shipment/";
	public static final String		listView	= module + "reportList";

	public static final String[]	reportNames	= new String[] { "REPORT_SHIPMENT_INITIAL" };
	public static final String[]	reportMaps	= new String[] { "shipmentReport/initial" };
	public static final String[]	reportView	= new String[] { module + "shipmentReportinitial" };

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listGet(HttpServletRequest request) throws IOException {

		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		List<String> orgIds = basisService.authorizedIds(username, Labels.ORGANIZATION, Labels.SHIPMENT);
		if (orgIds.isEmpty())
			return errorPage(language, Labels.ERR_UNAUTHORIZED, "No organizations authorized for shipment reporting");

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
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
			begda = c.getTime();
		}
		try {
			endda = Labels.dmyDF.parse(strEndda);
		} catch (Exception e) {
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.getActualMaximum(Calendar.DAY_OF_MONTH));
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

	@RequestMapping(value = "/initial", method = RequestMethod.POST)
	public ModelAndView report0(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");
		int client = getClient(session);

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		// String nextpage = request.getParameter(Labels.NEXTPAGE);
		String strBegda = request.getParameter("begda");
		String strEndda = request.getParameter("endda");

		// System.out.println("submitted report/cumulativeMh projectId " + strProjectId
		// + " begda : " + strBegda + " endda : " + strEndda + " Nextpage : " +
		// nextpage);

		Date begda;
		try {
			begda = Labels.ymdDF.parse(strBegda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "BEGDA not selected");
		}

		Date endda;
		try {
			endda = Labels.fullYMDdf.parse(strEndda + " 23:59:00");
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "ENDDA not selected");
		}

		String prevPage = "shipmentReport/list?begda=" + strBegda + "&endda=" + strEndda;

		List<ViewShipmentLineStatus> records = shipmentJdbcService.getShipmentLineStatus(client, begda, endda, "I");

		ModelAndView model = new ModelAndView(reportView[0]);
		model.addObject("records", records);
		model.addObject("begda", Labels.dmyDF.format(begda));
		model.addObject("endda", Labels.dmyDF.format(endda));
		model.addObject("term", Labels.dmyDF.format(begda) + " .. " + Labels.dmyDF.format(endda));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.TOTAL, language.getIconText(Labels.TOTAL));
		model.addObject(Labels.PREVPAGE, prevPage);

		model.addObject(Labels.PAGETITLE, language.getText(Labels.REPORTS));

		Map<String, String> reports = new LinkedHashMap<String, String>();

		for (int i = 0; i < reportMaps.length; i++) {
			reports.put(language.getText(reportNames[i]), reportMaps[i]);
		}
		model.addObject("reports", reports);

		model.addObject(Labels.PAGETITLE, language.getText(reportNames[0]));
		return model;
	}

}
