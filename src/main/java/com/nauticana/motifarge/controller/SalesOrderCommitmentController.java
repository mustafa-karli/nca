package com.nauticana.motifarge.controller;
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
import com.nauticana.motifarge.model.SalesOrderCommitment;
import com.nauticana.motifarge.model.SalesOrderCommitmentId;
import com.nauticana.sales.model.ViewCommitmentOrder;
import com.nauticana.sales.service.SalesJdbcService;

@Controller
@ResponseBody
@RequestMapping("/salesOrderCommitment")
public class SalesOrderCommitmentController extends AbstractController<SalesOrderCommitment, SalesOrderCommitmentId> {

	@Autowired
	SalesJdbcService salesJdbcService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView orders(HttpServletRequest request) {

        // Check for user and read authorization on table
    	HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");
        int client = getClient(session);

        // Read language of session
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        // Read data and assign to model and view object

		try {
			int materialId = Integer.parseInt(request.getParameter("materialId"));
			Date orderDeadLine = Labels.dmyDF.parse(request.getParameter("orderDeadLine"));
	        List<ViewCommitmentOrder> records = salesJdbcService.viewCommitmentOrders(materialId, client, orderDeadLine);
	        ModelAndView model = new ModelAndView("motifarge/myCommitmentOrders");

	        model.addObject("records", records);
	        model.addObject("language", language);
	        model.addObject("materialId", materialId);
	        model.addObject("orderDeadLine", orderDeadLine);
	        model.addObject("client", client);
	        model.addObject(Labels.PAGETITLE, language.getText("COMMITMENT_ORDERS"));
	        return model;
		} catch (Exception e) {
			e.printStackTrace();
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}

		
    }
	
	
}