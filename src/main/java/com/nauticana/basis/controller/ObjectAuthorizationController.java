package com.nauticana.basis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.ObjectAuthorization;
import com.nauticana.basis.model.ObjectAuthorizationId;
import com.nauticana.basis.service.AuthorityObjectActionService;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.Utils;

@Controller
@ResponseBody
@RequestMapping("/" + ObjectAuthorization.ROOTMAPPING)
public class ObjectAuthorizationController extends AbstractController<ObjectAuthorization, ObjectAuthorizationId>{

	@Autowired
	private AuthorityObjectActionService authorityObjectActionService;
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newGet(HttpServletRequest request) {
		ModelAndView model = super.newGet(request);
		ObjectAuthorization record = (ObjectAuthorization) model.getModel().get("record");
		String objectType     = request.getParameter("objectType");
		if (!Utils.emptyStr(objectType)) {
			record.getId().setObjectType(objectType);
			model.addObject("actionList", authorityObjectActionService.findAllStr(objectType));
			model.addObject(Labels.PREVPAGE, prevPage(record));
		}
		return model;
	}


}
