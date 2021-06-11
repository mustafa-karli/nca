package com.nauticana.purchase.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.purchase.model.ProposalToRfpDialog;
import com.nauticana.purchase.model.ProposalToRfpDialogId;

@Controller
@ResponseBody
@RequestMapping("/proposalToRfpDialog")
public class ProposalToRfpDialogController extends AbstractController<ProposalToRfpDialog, ProposalToRfpDialogId> {
	
	@RequestMapping(value = "/addMessage", method = RequestMethod.POST)
	public ModelAndView addMessage(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        String nextpage = request.getParameter(Labels.NEXTPAGE);
    	String id = request.getParameter("id");
    	String dtext = request.getParameter("dtext" + id);

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());
        try {
        	ProposalToRfpDialog entity = modelService.newEntityWithParentId(id);
        	entity.setDtext(dtext);
        	entity.setUsername(username);
            entity = modelService.create(entity);
			basisService.notifyUser(entity.getId().getProposalId(), tableName(), entity.getId().toString(), entity.getProposalToRfp().getValidUntil(), "NEW " + tableName() + " FROM " + username + " : " + dtext.substring(0, Math.min(dtext.length(),40)));
            if (Utils.emptyStr(nextpage))
                nextpage = modelService.parentLink(entity);
        } catch (Exception e) {
            return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
        }
        if (Utils.emptyStr(nextpage))
            return new ModelAndView("redirect:/");
        else
            return new ModelAndView("redirect:/" + nextpage);
	}
	
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @Override
    public ModelAndView newGet(HttpServletRequest request) {
    	ModelAndView model = super.newGet(request);
    	ProposalToRfpDialog record = (ProposalToRfpDialog) model.getModel().get("record");
    	record.setUsername((String) request.getSession(true).getAttribute(Labels.USERNAME));
    	return model;
    }

}