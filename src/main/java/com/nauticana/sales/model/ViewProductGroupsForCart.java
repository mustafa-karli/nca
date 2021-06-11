package com.nauticana.sales.model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ViewProductGroupsForCart implements Comparable<ViewProductGroupsForCart> {

	private int materialGroupId;
	private String caption;
	private Set<ViewProductsForCart> items = new HashSet<ViewProductsForCart>(0);
	private Set<ViewCommitment> cmts = new HashSet<ViewCommitment>(0);
//	private Set<ViewProductGroupsForCart> groups = new HashSet<ViewProductGroupsForCart>(0);
	
	private Set<ViewProductGroupsForCart> groups = new TreeSet<ViewProductGroupsForCart>(new Comparator<ViewProductGroupsForCart>(){
        public int compare(ViewProductGroupsForCart c1, ViewProductGroupsForCart c2){
        	return c1.getCaption().compareTo(c2.getCaption());
        }
	});
	
	
	
	public ViewProductGroupsForCart(int materialGroupId, String caption) {
		super();
		this.materialGroupId = materialGroupId;
		this.caption = caption;
	}

	public int getId() {
		return materialGroupId;
	}

	public void setId(int materialGroupId) {
		this.materialGroupId = materialGroupId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Set<ViewProductsForCart> getItems() {
		return items;
	}

	public void setItems(Set<ViewProductsForCart> items) {
		this.items = items;
	}

	public Set<ViewCommitment> getCmts() {
		return cmts;
	}

	public void setCmts(Set<ViewCommitment> cmts) {
		this.cmts = cmts;
	}

	public Set<ViewProductGroupsForCart> getGroups() {
		return groups;
	}

	public void setGroups(Set<ViewProductGroupsForCart> groups) {
		this.groups = groups;
	}

	@Override
	public int compareTo(ViewProductGroupsForCart arg0) {
		return getCaption().compareTo(arg0.getCaption());
	}

	
}
