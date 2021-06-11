package com.nauticana.basis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserMenu {

	private String menuCaption;
	private String pageCaption;
	private String menuIcon;
	private String pageIcon;
	private String url;
	private int menuOrder;
	private int pageOrder;
	
	public UserMenu(String pageCaption, String pageIcon, String url, String menuCaption, String menuIcon, int menuOrder, int pageOrder) {
		super();
		this.menuCaption = menuCaption;
		this.pageCaption = pageCaption;
		this.menuIcon = menuIcon;
		this.pageIcon = pageIcon;
		this.url = url;
		this.menuOrder = menuOrder;
		this.pageOrder = pageOrder;
	}

	@Id
	@Column(name="PAGE_CAPTION")
	public String getPageCaption() {
		return pageCaption;
	}

	public void setPageCaption(String pageCaption) {
		this.pageCaption = pageCaption;
	}

	@Column(name="PAGE_ICON")
	public String getPageIcon() {
		return pageIcon;
	}

	public void setPageIcon(String pageIcon) {
		this.pageIcon = pageIcon;
	}

	@Column(name="URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name="MENU_CAPTION")
	public String getMenuCaption() {
		return menuCaption;
	}

	public void setMenuCaption(String menuCaption) {
		this.menuCaption = menuCaption;
	}

	@Column(name="MENU_ICON")
	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	@Column(name="MENU_ORDER")
	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	@Column(name="PAGE_ORDER")
	public int getPageOrder() {
		return pageOrder;
	}

	public void setPageOrder(int pageOrder) {
		this.pageOrder = pageOrder;
	}


}
