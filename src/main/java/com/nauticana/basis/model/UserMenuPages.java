package com.nauticana.basis.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Id;

public class UserMenuPages {

	private String caption;
	private String icon;

	private ArrayList<String> captions;
	private ArrayList<String> icons;
	private ArrayList<String> urls;
	
	public UserMenuPages() {}

	public UserMenuPages(String caption, String icon, ArrayList<String> captions, ArrayList<String> icons, ArrayList<String> urls) {
		super();
		this.caption = caption;
		this.icon = icon;
		this.captions = captions;
		this.icons = icons;
		this.urls = urls;
	}

	@Id
	@Column(name="CAPTION")
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Column(name="ICON")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public ArrayList<String> getCaptions() {
		return captions;
	}

	public void setCaptions(ArrayList<String> captions) {
		this.captions = captions;
	}

	public ArrayList<String> getIcons() {
		return icons;
	}

	public void setIcons(ArrayList<String> icons) {
		this.icons = icons;
	}

	public ArrayList<String> getUrls() {
		return urls;
	}

	public void setUrls(ArrayList<String> urls) {
		this.urls = urls;
	}

}
