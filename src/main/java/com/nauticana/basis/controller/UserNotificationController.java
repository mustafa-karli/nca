package com.nauticana.basis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.UserNotification;
import com.nauticana.basis.repository.BasisRepository;
import com.nauticana.basis.service.UserNotificationService;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;

@Controller
@ResponseBody
@RequestMapping("/userNotification")
public class UserNotificationController extends AbstractController<UserNotification, Long>{
	
	
	@RequestMapping(value = "/myNotifications", method = RequestMethod.GET)
	public ModelAndView getNotifications(HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username))
			return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		ModelAndView model = new ModelAndView("basis/myNotifications");
		model.addObject("language", language);
		model.addObject("statics", BasisRepository.controllerStatics);
		model.addObject("notifications", ((UserNotificationService) modelService).activeUserNotifications(username));

		return model;
	}
	
	
		@RequestMapping(value = "/complete")
		public ModelAndView complete(HttpServletRequest request) {
			HttpSession session = request.getSession(true);
			String username = (String) session.getAttribute(Labels.USERNAME);
			if (Utils.emptyStr(username))
				return new ModelAndView("redirect:/");
			PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
			try {
				String idStr = request.getParameter("id");
				
				Long id = modelService.strToId(idStr);
				UserNotification record = modelService.findById(id);
				record.setStatus("C");
				modelService.save(record);
				return new ModelAndView("redirect:/userNotification/myNotifications");
			} catch (Exception e) {
				return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
			}
		}
		
		@ResponseBody
		@RequestMapping(value = "/restComplete/{idStr}", method = RequestMethod.POST)
		public String restComplete(HttpServletRequest request, @PathVariable String idStr) {
			HttpSession session = request.getSession(true);
			String username = (String) session.getAttribute(Labels.USERNAME);
			if (Utils.emptyStr(username))
				return  "User not loged in";
			try {
				Long id = modelService.strToId(idStr);
				UserNotification record = modelService.findById(id);
				record.setStatus("C");
				modelService.save(record);
				return "Notification Completed";
			} catch (Exception e) {
				return e.getMessage();
			}
		}
}
