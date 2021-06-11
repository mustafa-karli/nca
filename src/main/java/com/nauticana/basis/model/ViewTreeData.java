package com.nauticana.basis.model;

import java.util.ArrayList;
import java.util.List;

public class ViewTreeData {

	private int id;
	private String caption;
	private ViewTreeData parent = null;
	private List<ViewTreeData> children = null;
	private List<Object> items = null;
	
	public ViewTreeData(int id, String caption) {
		super();
		this.id = id;
		this.caption = caption;
	}

	public int getId() {
		return id;
	}

	public String getCaption() {
		return caption;
	}

	public ViewTreeData getParent() {
		return parent;
	}

	public List<ViewTreeData> getChildren() {
		return children;
	}

	public void setChildren(List<ViewTreeData> children) {
		this.children = children;
	}

	public void addChild(ViewTreeData child) {
		if (this.children == null) this.children = new ArrayList<ViewTreeData>();
		this.children.add(child);
		child.parent = this;
	}

	public List<Object> getItems() {
		return items;
	}

	public void setItems(List<Object> items) {
		this.items = items;
	}
	
	public void addItem(Object item) {
		if (this.items == null) this.items = new ArrayList<Object>();
		this.items.add(item);
	}
	
	
}
