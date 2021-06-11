package com.nauticana.business.controller;
import java.io.IOException;

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
import com.nauticana.business.model.Vendor;
import com.nauticana.personnel.controller.EmployeeController;

@Controller
@ResponseBody
@RequestMapping("/vendor")
public class VendorController extends AbstractController<Vendor, Integer> {
	
    @Autowired
    protected BusinessPartnerController					businessPartnerController;

    @Autowired
    protected EmployeeController						employeeController;
	
	@Override
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editGet(HttpServletRequest request) {
		ModelAndView model = businessPartnerController.editGet(request);
		model.addObject(Labels.PREVPAGE, getControllerStatic().getRootMapping() + "/list");
		return model;
	}
	
	@Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newGet(HttpServletRequest request) {
		ModelAndView model = businessPartnerController.newGet(request);
		model.addObject(Labels.PREVPAGE, getControllerStatic().getRootMapping() + "/list");
		model.addObject(Labels.NEXTPAGE, getControllerStatic().getRootMapping() + "/list");
		return model;
	}
	
    @RequestMapping(value = "/empuser", method = RequestMethod.GET)
    public ModelAndView empUserGet(HttpServletRequest request) throws IOException {
		ModelAndView model = employeeController.empUserList(request);
		model.addObject(Labels.PREVPAGE, getControllerStatic().getRootMapping() + "/list");
		model.addObject(Labels.NEXTPAGE, getControllerStatic().getRootMapping() + "/list");
		return model;
	}
	
    @RequestMapping(value = "/approve", method = RequestMethod.GET)
	public ModelAndView approveGet(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/userAccount/login");

        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        try {
        	int id = Integer.parseInt(request.getParameter("id"));
            if (!basisService.authorityChk(username, tableName(), Labels.APPROVE, id+""))
                return errorPage(language, Labels.ERR_UNAUTHORIZED, tableName() + "-" + Labels.APPROVE + " ON " + id);
            String nextpage = request.getParameter(Labels.NEXTPAGE);
            Vendor record = modelService.findById(id);
            record.setApproved("Y");
            modelService.save(record);
    		if (Utils.emptyStr(nextpage))
    			return new ModelAndView("redirect:list");
    		else
    			return new ModelAndView("redirect:/" + nextpage);
		} catch (Exception e) {
            return errorPage(language, Labels.ERR_BINDING, "id not found on http request");
		}
	}
}

