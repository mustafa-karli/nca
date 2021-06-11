package com.nauticana.basis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.model.PartnerConversation;
import com.nauticana.basis.model.UserMenu;
import com.nauticana.basis.model.UserMenuPages;
import com.nauticana.basis.model.UserNotification;
import com.nauticana.basis.model.ViewTreeData;
import com.nauticana.basis.repository.BasisRepository;
import com.nauticana.basis.utils.ControllerStatic;
import com.nauticana.basis.utils.FieldType;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;

@Service
public class BasisService {

	@Autowired
	private BasisRepository r;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private UserNotificationService userNotificationService;
	
	@Autowired
	private NotificationTypeService notificationTypeService;

	public List<UserMenu> getUserMenu(String username, PortalLanguage language) {
		List<UserMenu> records = r.userMenu(username);
		for (UserMenu menu : records) {
			menu.setMenuCaption(language.getText(menu.getMenuCaption()));
			menu.setPageCaption(language.getText(menu.getPageCaption()));
		}
		return records;
	}
	
	public List<UserMenuPages> getUserMenuPages(String username, PortalLanguage language) {
		List<UserMenu> records = r.userMenu(username);
		List<UserMenuPages> pages = new ArrayList<UserMenuPages>();
		UserMenuPages last = null;
		for (UserMenu menu : records) {
			menu.setMenuCaption(language.getText(menu.getMenuCaption()));
			menu.setPageCaption(language.getText(menu.getPageCaption()));
			if (last == null || !last.getCaption().equals(menu.getMenuCaption())) {
				last = new UserMenuPages(menu.getMenuCaption(), menu.getMenuIcon(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
				pages.add(last);
			}
			last.getCaptions().add(menu.getPageCaption());
			last.getIcons().add(menu.getPageIcon());
			last.getUrls().add(menu.getUrl());
		}
		return pages;
	}
	
	public String getUserMenuHtml(String username, PortalLanguage language) {
		List<UserMenu> pages = getUserMenu(username, language);
		String menu = " <ul class=\"sidebar-menu\">\n";
		String last = "";

		for (UserMenu page : pages) {
			if (!page.getMenuCaption().equals(last)) {
				if (!Utils.emptyStr(last))	menu = menu+"   </ul>\n  </li>";
				last = page.getMenuCaption();
				menu=menu+"  <li class=\"treeview active\"> <a href=\"#\"><i class=\"fa " + page.getMenuIcon() + "\"></i> <span> " + last + " </span> <span class=\"pull-right-container\"> </span> </a>\n   <ul class=\"treeview-menu menu-open\" style=\"display: block;\">\n";
			}
			menu=menu+"    <li> <a href=\"#\" onClick=\"doAjaxGet('" + page.getUrl() + "');\"> <i class=\"fa " + page.getPageIcon() + "\"> " + page.getPageCaption() + " </i> </a> </li>" + System.lineSeparator();
		}
		if (!Utils.emptyStr(last))	menu = menu+"   </ul>\n  </li>\n";
		return menu+" </ul>\n";
	}

    public List<ViewTreeData> treeData(String sql) {
    	return r.treeData(sql);
    }
    
	public List<String> tableDomainList(String tableName) {
		return r.tableDomainsList(tableName);
	}

	public String[] tableDomains(String tableName) {
		return r.tableDomains(tableName);
	}

	public boolean authorityChk(String username, String objectType, String action, String keyValue) {
		return r.authorityChk(username, objectType, action, keyValue);
	}

	public boolean authorityChk(String username, String objectType, String action) {
		return r.authorityChk(username, objectType, action);
	}

	public List<String> authorityObjectTypes() {
		return r.authorityObjectTypes();
	}

	public List<String> authorityObjectActions(String authorityObject) {
		return r.authorityObjectActions(authorityObject);
	}

	public List<String> authorizedIds(String username, String authorityObject, String action) {
		return r.authorizedObjects(username, authorityObject, action);
	}

	public FieldType[] fieldTypes(String tableName) {
		return r.fieldTypes(tableName);
	}

	public ControllerStatic getControllerStatic(String tableName) {
		return r.getControllerStatic(tableName);
	}

	public String[] userFavorites(String username, String favType) {
		return r.userFavorites(username, favType);
	}

	public int[] userFavoritesInt(String username, String favType) {
		String[] s = r.userFavorites(username, favType);
		int[] l = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			l[i] = Integer.parseInt(s[i]);
		}
		return l;
	}

	public int addUserFavorite(String username, String favType, String objid, String description) {
		return r.addUserFavorite(username, favType, objid, description);
	}
	
	public int addUserRole(String username, String authorityGroup, Date begda, Date endda) {
		return r.addUserRole(username, authorityGroup, begda, endda);
	}
	
    public List<String[]> notificationUser(int client, String tablename, String event) {
    	return r.notificationUser(client, tablename, event);
    }

    public List<PartnerConversation> partnerConversations(String objectType, int objectId) {
    	return r.partnerConversations(objectType, objectId);
    }

    public void addPartnerConversation(String objectType, int objectId, int fromPartner, int toPartner, String username, String ctext) {
    	r.addPartnerConversation(objectType, objectId, fromPartner, toPartner, username, ctext);
    }

	public List<UserNotification> notifyUser(int client, String tablename, String objectId, Date dueDate, String decription) throws Exception {
		List<String[]> notificationTypes = notificationUser(client, tablename, "CREATE");
		ArrayList<UserNotification> notifications = new ArrayList<UserNotification>();

		for (String[] sl : notificationTypes) {
			UserNotification un = userNotificationService.newEntityWithParentId(null);
			un.setNotificationType(notificationTypeService.findById(Integer.parseInt(sl[0])));
			un.setObjectId(objectId);
			un.setUserAccount(userAccountService.findById(sl[1]));
			un.setRaiseDate(new Date());
			un.setStatus("I");
			un.setDescription(decription);
			un.setDueDate(dueDate);
			notifications.add(userNotificationService.create(un));
		}
		return notifications;
	}


	public List<String[]> freeQueryString(String sql) {
		return r.freeQueryString(sql);
	}
	
	public String arrayToJSON(List<String[]> list) {
		String jsonTxt = "";
		for (String[] s : list) {
			jsonTxt = jsonTxt + ",{\"key\":\"" + s[0] + "\",\"txt\":\"" + s[1] + "\"}";
		}
		if (jsonTxt.equals(""))
			jsonTxt = "[]";
		else
			jsonTxt = "[" + jsonTxt.substring(1) + "]"; 
		return jsonTxt;
	}

}
