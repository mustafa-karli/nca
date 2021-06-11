package com.nauticana.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;

@Entity
public class ViewProjectCategory {

	private int categoryId;
	private Integer parentId;
	private String treeCode;
	private String caption;
	private int catLevel;
	private String projectId;
	private String projectFilter;
	public String expended = "";
	public String checked = "";
	public String links = "";
	public boolean hasChild = false;
	
	public ViewProjectCategory(int categoryId, Integer parentId, String treeCode, String caption, int catLevel, String projectId, String projectFilter) {
		super();
		this.categoryId = categoryId;
		this.parentId = parentId;
		this.treeCode = treeCode;
		this.caption = caption;
		this.catLevel = catLevel;
		this.projectId = projectId;
		this.projectFilter = projectFilter;
	}


	@Id
	@Column(name="CATEGORY_ID")
	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	@Column(name="PARENT_ID")
	public Integer getParentId() {
		return parentId;
	}


	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	@OrderBy("TREE_CODE")
	@Column(name="TREE_CODE")
	public String getTreeCode() {
		return treeCode;
	}


	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}


	@Column(name="CAPTION")
	public String getCaption() {
		return caption;
	}


	public void setCaption(String caption) {
		this.caption = caption;
	}


	@Column(name="CAT_LEVEL")
	public int getCatLevel() {
		return catLevel;
	}


	public void setCatLevel(int catLevel) {
		this.catLevel = catLevel;
	}


	@Column(name="PROJECT_ID")
	public String getProjectId() {
		return projectId;
	}


	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Column(name="PROJECT_FILTER")
	public String getProjectFilter() {
		return projectFilter;
	}


	public void setProjectFilter(String projectFilter) {
		this.projectFilter = projectFilter;
	}
	
}
