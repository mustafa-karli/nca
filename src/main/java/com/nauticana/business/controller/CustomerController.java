package com.nauticana.business.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.utils.Labels;
import com.nauticana.business.model.Customer;

@Controller
@ResponseBody
@RequestMapping("/" + Customer.ROOTMAPPING)
public class CustomerController extends AbstractController<Customer, Integer> {
	
    @Autowired
    protected BusinessPartnerController					businessPartnerController;
	
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
		return model;
	}

}

