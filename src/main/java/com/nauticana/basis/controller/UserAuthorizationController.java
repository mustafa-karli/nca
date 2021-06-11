package com.nauticana.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.UserAuthorization;
import com.nauticana.basis.model.UserAuthorizationId;

@Controller
@ResponseBody
@RequestMapping("/" + UserAuthorization.ROOTMAPPING)
public class UserAuthorizationController extends AbstractController<UserAuthorization, UserAuthorizationId>{
	
//	@Override
//	@RequestMapping(value = "/new", method = RequestMethod.POST)
//    public ModelAndView newPost(@ModelAttribute UserAuthorization record, BindingResult result, HttpServletRequest request) {
//        HttpSession session = request.getSession(true);
//        String username = (String) session.getAttribute(Labels.USERNAME);
//        if (Utils.emptyStr(username))
//            return new ModelAndView("redirect:/");
//
//        // Read language of session
//        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
//
//        if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
//            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());
//
//        try {
//            basisService.addUserRole(record.getId().getUsername(), record.getId().getAuthorityGroup(), new Date());
//        } catch (Exception e) {
//            return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
//        }
//        String nextpage = request.getParameter(Labels.NEXTPAGE);
//        if (Utils.emptyStr(nextpage))
//            return new ModelAndView("redirect:/userAccount/show?id="+record.getId().getUsername());
//        else
//            return new ModelAndView("redirect:/" + nextpage);
//    }

	
}
