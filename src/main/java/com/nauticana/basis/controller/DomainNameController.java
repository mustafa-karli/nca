package com.nauticana.basis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.DomainName;

@Controller
@ResponseBody
@RequestMapping("/" + DomainName.ROOTMAPPING)
public class DomainNameController extends AbstractController<DomainName, String> {

	@RequestMapping(value="/reloadValues",method=RequestMethod.GET)
	public ModelAndView reloadValues(HttpServletRequest request) {
		String id = request.getParameter("id");
		DomainName domainName = modelService.findById(id);
		dataCache.getDomain(id).clear();
		dataCache.loadDomainValues(domainName);
		return new ModelAndView("redirect:show?id="+id);
	}

}
