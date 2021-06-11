package com.nauticana.basis.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.abstrct.AbstractController;
import com.nauticana.basis.model.Language;
import com.nauticana.basis.model.LoginBean;
import com.nauticana.basis.model.UserAccount;
import com.nauticana.basis.service.LanguageService;
import com.nauticana.basis.service.UserAccountService;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.NamsSession;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.personnel.model.Employee;
import com.nauticana.personnel.model.PositionAssignment;
import com.nauticana.personnel.model.UserAccountOwner;
import com.nauticana.personnel.service.OrganizationService;

@Controller
@ResponseBody
@RequestMapping("/userAccount")
public class UserAccountController extends AbstractController<UserAccount, String> {

	@Autowired
	private LanguageService languageService;

	@Autowired
	protected OrganizationService					organizationService;

	public static final String     module       = "basis/";

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView loginGet(HttpServletRequest request) throws IOException{
		ModelAndView model = new ModelAndView(module + "login");
		LoginBean loginBean = new LoginBean();
		PortalLanguage language = dataCache.getLanguage(request.getLocale().getCountry().toUpperCase());
		if (language == null) {
			loginBean.setLanguage("EN");
			language = dataCache.getLanguage("EN");
		} else
			loginBean.setLanguage(language.code);
        model.addObject("loginBean", loginBean);
		model.addObject(Language.TABLE_NAME, dataCache.getLookupOptions(Language.TABLE_NAME, 0));
		model.addObject(Labels.APPLICATION_TITLE, language.getText(Labels.APPLICATION_TITLE));
		return model;
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView loginPost(HttpServletRequest request, @ModelAttribute("loginBean")LoginBean loginBean) {
		String uname = loginBean.getUsername();
		UserAccount userAccount = modelService.findById(uname);
		if (userAccount == null) {
			List<UserAccount> userAccounts = ((UserAccountService) modelService).findByEmail(uname.toLowerCase(Locale.US));
			if (!userAccounts.isEmpty())
				userAccount = userAccounts.get(0);
		}
		boolean ok = true;
		String message = "";

		if (userAccount == null) {
//			message = "User not found!";
			message = "Invalid credentials!";
			ok = false;
		} else {
			switch (userAccount.getStatus().charAt(0)) {
				case 'A':
					ok = userAccount.checkPassword(loginBean.getPassword());
					if (!ok)
						message = "Invalid credentials!";
					break;
				case 'I':
					message = "User is initial, call support center!";
					break;
				case 'E':
					message = "Account has expired, call support center!";
					break;
				case 'L':
					ok = false;
					message = "User is locked!";
					break;
				case 'S':
					ok = false;
					message = "User is locked by system administrator!";
					break;

				default:
					break;
			}
		}
		
		if (ok) {
			// Set session variables
			int personId = -1;
			int OwnerId = -1;
			int organizationId = -1;
			String position = "";
			HttpSession session = request.getSession(true);
			session.setAttribute(Labels.USERNAME, userAccount.getId());
			session.setAttribute(Labels.USER_CAPTION, userAccount.getFirstName() + " " + userAccount.getLastName());
			session.setAttribute(Labels.LANGUAGE, loginBean.getLanguage());
			for (UserAccountOwner owner : userAccount.getUserAccountOwners()) {
				personId = owner.getPerson().getId();
				for(Employee emp : owner.getPerson().getEmployees()) {
					OwnerId = emp.getOwnerId();
					for (PositionAssignment po : emp.getPositionAssignments())
					if (po.getEndda().after(new Date())) {
						organizationId = po.getId().getOrganizationId();
						position = po.getId().getPosition();
					}
					
				}
			}
			session.setAttribute(Labels.PERSON_ID, personId);
			session.setAttribute(Labels.OWNER_ID, OwnerId);
			session.setAttribute(Labels.ORGANIZATION_ID, organizationId);
			session.setAttribute(Labels.POSITION, position);
			String orgs = "";
			try {
				List<String> ao = basisService.authorizedIds(uname, Labels.ORGANIZATION, Labels.DATAFILTER);
				if (ao.isEmpty()) {
					List<Integer> orgIds = organizationService.findAllChildren(organizationId);
					orgs = orgIds.get(0)+"";
					for (int i = 1; i < orgIds.size(); i++) {
						orgs = orgs+","+orgIds.get(i);
					}
				} else {
					orgs = ao.get(0)+"";
					for (int i = 1; i < ao.size(); i++) {
						orgs = orgs+","+ao.get(i);
					}
				}
			} catch (Exception e) {
			}
			session.setAttribute(Labels.ORGANIZATION_IDS, orgs);
			
			PortalLanguage language = dataCache.getLanguage(loginBean.getLanguage());
			if ("RIGHT".equals(languageService.findById(loginBean.getLanguage()).getDirection()))
				session.setAttribute(Labels.LANGUAGE_DIRECTION, " dir=\"RTL\"");
			else
				session.setAttribute(Labels.LANGUAGE_DIRECTION, "");
			// Prepare left menu
			String menu = basisService.getUserMenuHtml(uname, language);
//			System.out.println(menu);
			session.setAttribute(Labels.MENU, menu);
			dataCache.putSession(null, uname, personId, OwnerId, language.code, basisService.getUserMenu(uname, language));
//			System.out.println("User Login Successful : " + uname);
			if (userAccount.getStatus().charAt(0) == 'I' || userAccount.getStatus().charAt(0) == 'E')
				return new ModelAndView("redirect:setPassword?mode=I");
			return new ModelAndView("redirect:/");
		} else {
			ModelAndView model = new ModelAndView(module + "login");
			model.addObject("loginBean", loginBean);
			model.addObject(Language.TABLE_NAME, dataCache.getLookupOptions(Language.TABLE_NAME, 0));
			model.addObject("message", message);
//			System.out.println("User Login Error : " + message);
	        return model;
	    }
	}

	@RequestMapping(value="/logoff",method=RequestMethod.GET)
	public ModelAndView logoff(HttpServletRequest request) throws IOException{
		// Set session variables
		HttpSession session = request.getSession(true);
		session.removeAttribute(Labels.USERNAME);
		session.removeAttribute(Labels.LANGUAGE);
		session.removeAttribute(Labels.MENU);
		return new ModelAndView("redirect:/");
	}

	@Override
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editPost(@ModelAttribute UserAccount record, BindingResult result, HttpServletRequest request) {
		if (Utils.emptyStr(record.getPasstext())) {
			UserAccount original = modelService.findById(record.getId());
			record.setPasstext(original.getPasstext());
		}
		return super.editPost(record, result, request);
	}

	@RequestMapping(value = "/setPassword", method = RequestMethod.GET)
	public ModelAndView setPasswordGet(HttpServletRequest request) {
		// Check for user and insert authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		String prevpage = "/";
		String mode = (String) request.getParameter("mode");
		if ("I".equals(mode))
			prevpage = "login";
			
		ModelAndView model = new ModelAndView(module + "setPassword");
		model.addObject("userAccountName", username);
		model.addObject("language", language);
		model.addObject(Labels.PAGETITLE, language.getText(Labels.CHANGE_PASSWORD));
		model.addObject(Labels.SAVE, language.getIconText(Labels.SAVE));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.POSTLINK, getControllerStatic().getRootMapping()+"/setPassword");
		model.addObject(Labels.PREVPAGE, prevpage);
		model.addObject(Labels.APPLICATION_TITLE, language.getText(Labels.APPLICATION_TITLE));
		return model;
	}

	@RequestMapping(value = "/setPassword", method = RequestMethod.POST)
	public ModelAndView setPasswordPost(HttpServletRequest request) {
		// Check for user and insert authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		String userAccountName = request.getParameter("userAccountName");
		String passText        = request.getParameter("passText");
		
		if (Utils.emptyStr(passText) || !username.equals(userAccountName)) return new ModelAndView("redirect:/");

		UserAccount entity = modelService.findById(userAccountName);
		if (entity == null) return new ModelAndView("redirect:/");
		entity.setPasstext(passText);
		entity.setStatus("A");
		try {
			modelService.save(entity);
		} catch(Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		return new ModelAndView("redirect:/");
	}
	
	/*
	 * Additional REST Controllers
	 * 
	 * */
	
	@PostMapping(value = "/restLogin")
	public ResponseEntity<NamsSession> restLogin(HttpServletRequest request, @RequestBody LoginBean loginBean) throws Exception {
		UserAccount userAccount = modelService.findById(loginBean.getUsername());
		if (userAccount == null) return new ResponseEntity<NamsSession>(HttpStatus.UNAUTHORIZED);
		if (!userAccount.checkPassword(loginBean.getPassword())) return new ResponseEntity<NamsSession>(HttpStatus.UNAUTHORIZED);
		switch (userAccount.getStatus().charAt(0)) {
		case 'L': return new ResponseEntity<NamsSession>(HttpStatus.LOCKED);
		case 'S': return new ResponseEntity<NamsSession>(HttpStatus.NOT_ACCEPTABLE);
		case 'A': break;
		case 'I': break;
		case 'E': break;
		default : break;
		}
		
		int personId = 0;;
		int client   = 0;
		for (UserAccountOwner owner : userAccount.getUserAccountOwners()) {
			for(Employee emp : owner.getPerson().getEmployees()) {
				personId = emp.getPerson().getId();
				client = emp.getOwnerId();
			}
		}
		PortalLanguage language = dataCache.getLanguage(loginBean.getLanguage());
		return new ResponseEntity<NamsSession>(dataCache.putSession(null, loginBean.getUsername(), personId, client, loginBean.getLanguage(), basisService.getUserMenu(loginBean.getUsername(), language)), HttpStatus.OK);
	}

}
