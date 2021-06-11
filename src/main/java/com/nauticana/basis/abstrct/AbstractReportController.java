package com.nauticana.basis.abstrct;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.service.BasisService;
import com.nauticana.basis.utils.DataCache;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;

public abstract class AbstractReportController {

	@Autowired
	protected DataCache dataCache;

	@Autowired
	protected BasisService basisService;

	public static int getClient(HttpSession session) {
		try {
			return (int) session.getAttribute(Labels.OWNER_ID);
		} catch (Exception e) {
			return -1;
		}
	}

	public ModelAndView errorPage(PortalLanguage language, String ERRCODE, String msgText) {
		ModelAndView model = new ModelAndView("basis/errorPage");
		model.addObject("ERRCODE", language.getText(ERRCODE));
		model.addObject("MSGTEXT", msgText);
		return model;
	}
	

}
