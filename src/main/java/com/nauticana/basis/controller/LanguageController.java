package com.nauticana.basis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.Language;
import com.nauticana.basis.utils.PortalLanguage;

@Controller
@ResponseBody
@RequestMapping("/" + Language.ROOTMAPPING)
public class LanguageController extends AbstractController<Language, String> {

	@RequestMapping(value="/reloadTranslation",method=RequestMethod.GET)
	public ModelAndView reloadTranslation(HttpServletRequest request) {
		String id = request.getParameter("id");
		Language language = modelService.findById(id);
		PortalLanguage pl = dataCache.getLanguage(id);
		pl.translations.clear();
		pl.iconText.clear();
		pl.icon.clear();
		dataCache.loadTranslations(language);
		return new ModelAndView("redirect:show?id="+id);
	}
}
