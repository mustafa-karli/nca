package com.nauticana.basis.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.model.Language;
import com.nauticana.basis.model.UserNotification;
import com.nauticana.basis.repository.BasisRepository;
import com.nauticana.basis.service.BasisService;
import com.nauticana.basis.service.LanguageService;
import com.nauticana.basis.service.UserNotificationService;
import com.nauticana.basis.utils.DataCache;
import com.nauticana.basis.utils.Domain;
import com.nauticana.basis.utils.Icons;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;
import com.nauticana.business.model.ApplicationConfig;
import com.nauticana.material.service.MaterialJdbcService;
import com.nauticana.sales.service.SalesJdbcService;

@Controller
public class HomeController {

	public static final String DEFAULT_HOME = "home/adminlte310";
	
	@Autowired
	private BasisService utilService;

	@Autowired
	private LanguageService languageService;

	@Autowired
	private DataCache dataCache;

	@Autowired
	SalesJdbcService salesJdbcService;

	@Autowired
	MaterialJdbcService materialJdbcService;
	
	@Autowired
	UserNotificationService userNotificationService;
	
	@RequestMapping(value="/")
	public ModelAndView home(HttpServletRequest request) throws IOException{
		HttpSession session = request.getSession(true);
		String langcode = request.getParameter("langcode");
		if (!Utils.emptyStr(langcode)) {
			session.setAttribute(Labels.LANGUAGE, langcode);
			if ("RIGHT".equals(languageService.findById(langcode).getDirection()))
				session.setAttribute(Labels.LANGUAGE_DIRECTION, " dir=\"RTL\"");
			else
				session.setAttribute(Labels.LANGUAGE_DIRECTION, "");
		}
		langcode = (String) session.getAttribute(Labels.LANGUAGE);
		if (Utils.emptyStr(langcode)) {
			langcode="EN";
			session.setAttribute(Labels.LANGUAGE, langcode);
		}
		PortalLanguage language = dataCache.getLanguage(langcode);
		String username = (String) session.getAttribute(Labels.USERNAME);
//		return new ModelAndView("redirect:/userAccount/login");
//		if (!Utils.emptyStr(username)) {
//			String menu = utilService.getUserMenuHtml(username, language);
////			System.out.println(menu);
//			session.setAttribute(Labels.MENU, menu);
//		}

		ApplicationConfig applicationConfig = null;
		String homepage;
		String initialContent="";
		String logo = "contentRelation/firstData/LG/1";
		String logomini = "contentRelation/firstData/LM/1";
		int client = 1;
		try {
			client = (int) session.getAttribute(Labels.OWNER_ID);
			applicationConfig = dataCache.getApplicationConfig(client);
		} catch (Exception e) {
			String domain = request.getServerName();
			applicationConfig = dataCache.getApplicationConfig(domain);
		}
		if (applicationConfig != null) {
			logo = "contentRelation/firstData/LG/" + applicationConfig.getId();
			logomini = "contentRelation/firstData/LM/" + applicationConfig.getId();
		}

		homepage = request.getParameter("home");
		if (Utils.emptyStr(homepage))
		try {
			homepage = applicationConfig.getHomepage();
			initialContent = applicationConfig.getHomepage();
		} catch (Exception e) {
			homepage = DEFAULT_HOME;
		} else
			homepage = "home/" + homepage;
		
		
		ModelAndView model = new ModelAndView(homepage);
		model.addObject("PAGETITLE", language.getText(Labels.APPLICATION_TITLE));
		model.addObject(Labels.LANGUAGE_DIRECTION, session.getAttribute(Labels.LANGUAGE_DIRECTION));
		model.addObject("logo", logo);
		model.addObject("logomini", logomini);
		model.addObject("initialContent", initialContent);
		model.addObject(Icons.MENU, Icons.getIcon(Icons.MENU));
		model.addObject(Icons.LOGIN, Icons.getIcon(Icons.LOGIN));
		model.addObject(Icons.LOGOFF, Icons.getIcon(Icons.LOGOFF));
//		model.addObject("menu", (String) session.getAttribute(Labels.MENU));
		model.addObject("activeLang", session.getAttribute(Labels.LANGUAGE));
		if (username != null) {
			model.addObject("userCaption", session.getAttribute(Labels.USER_CAPTION));
			model.addObject("usermenu", utilService.getUserMenuPages(username, language));
			List<UserNotification> notifications = userNotificationService.activeUserNotifications(username);
			for (UserNotification notification : notifications) {
				utilService.getControllerStatic(notification.getNotificationType().getTablename());
			}
			model.addObject("notifications", notifications);
		}
		model.addObject("statics", BasisRepository.controllerStatics);
		Language l = languageService.findById((String) session.getAttribute(Labels.LANGUAGE));
		if (l != null)
			model.addObject("langClass", "flag-icon " + l.getFlag());
		model.addObject("languageList", getLanguages());

		model.addObject("productGroups", salesJdbcService.productGroupsForCommitment(Utils.onlyDate(), "MOTIFSALES"));
		model.addObject("materialAttributeGroups", materialJdbcService.materialAttrGroupByMatGroup(2251));
		model.addObject("manufacturer", dataCache.getLookupOptions("MANUFACTURER", -1));
		
		return model;
	}
	
	public Map<String, String> getLanguages() {
		String [][] s = languageService.findAllFlag();
		Map<String,String> items = new LinkedHashMap<String, String>();
		for (int j = 0; j < s.length; j++) {
			items.put(s[j][0], s[j][1]);
		}
		return items;
	}

	
    @GetMapping("/selectOptions")
    public ResponseEntity<String> selectOptions(HttpServletRequest request) {
    	String set = request.getParameter("set");
    	int client;
    	try {client = Integer.parseInt(request.getParameter("client"));} catch (Exception e) {client = -1;}
    	Domain domain = dataCache.getDomain(set);
    	String result = null;
    	if (domain != null) result = domain.json();
    	if (result == null) result = dataCache.getLookupJson(set, client);
    	if (result == null) return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    	return new ResponseEntity<String>(result, HttpStatus.OK);
    }
	
    @GetMapping("/tableOptions")
    public ResponseEntity<String> tableOptions(HttpServletRequest request) {
    	String tableName = request.getParameter("table");
    	String fields = request.getParameter("fields");
    	String where = request.getParameter("where");
    	String filter = request.getParameter("filter");
    	String orderby = request.getParameter("orderby");
    	String sql;
    	if (Utils.emptyStr(where))
    		sql = "SELECT " + fields + " FROM " + tableName;
    	else
    		sql = "SELECT " + fields + " FROM " + tableName + " WHERE " + where + "='" + filter + "'";
    	if (!Utils.emptyStr(orderby))
    		sql = sql + " ORDER BY " + orderby;
    	List<String[]> list = utilService.freeQueryString(sql);
    	String result = utilService.arrayToJSON(list);
    	if (result == null) return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    	return new ResponseEntity<String>(result, HttpStatus.OK);
    }
	
	
}
