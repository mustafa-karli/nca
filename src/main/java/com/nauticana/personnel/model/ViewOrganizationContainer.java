package com.nauticana.personnel.model;

import java.util.List;

public class ViewOrganizationContainer {

	private int id;
	private String caption;
	private List<ViewOrganizationContainer> children = null;
	private List<Object> items = null;
	
	public ViewOrganizationContainer(int id, String caption) {
		super();
		this.id = id;
		this.caption = caption;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public List<ViewOrganizationContainer> getChildren() {
		return children;
	}

	public void setChildren(List<ViewOrganizationContainer> children) {
		this.children = children;
	}

	public List<Object> getItems() {
		return items;
	}

	public void setItems(List<Object> items) {
		this.items = items;
	}

	
}
