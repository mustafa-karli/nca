package com.nauticana.project.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PROJECT_WBS_MH_TO_APR")
public class ViewProjectWbsMhApprove {

	public static final String tableName = "PROJECT_WBS_MH_TO_APR";
	public static final String[] fieldNames = new String[] {"PROJECT_ID", "CATEGORY_ID", "TEAM_ID", "ACTIVITY_DATE", "PROJECT_CAPTION", "TREE_CODE", "CATEGORY_CAPTION", "TEAM_CAPTION", "UNIT", "METRIC", "QUANTITY", "WORKFORCE", "APPROVED_MANHOUR", "MANHOUR_TO_APPROVE" };
	
	private ViewProjectWbsMhApproveId id;
	private String projectCaption;
	private String treeCode;
	private String categoryCaption;
	private String teamCaption;
	private String unit;
	private float  metric;
	private float  quantity;
	private Float  approvedManhour;
	private Float  manhourToApprove;
	

	public ViewProjectWbsMhApprove() {
	}
	
	
	public ViewProjectWbsMhApprove(ViewProjectWbsMhApproveId id, String projectCaption, String treeCode, String categoryCaption,
			String teamCaption, String unit, float metric, float quantity, Float approvedManhour, Float manhourToApprove) {
		super();
		this.id = id;
		this.projectCaption = projectCaption;
		this.treeCode = treeCode;
		this.categoryCaption = categoryCaption;
		this.teamCaption = teamCaption;
		this.unit = unit;
		this.metric = metric;
		this.quantity = quantity;
		this.approvedManhour = approvedManhour;
		this.manhourToApprove = manhourToApprove;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "categoryId", column = @Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "teamId", column = @Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)) })
	public ViewProjectWbsMhApproveId getId() {
		return id;
	}

	public void setId(ViewProjectWbsMhApproveId id) {
		this.id = id;
	}

	@Column(name="PROJECT_CAPTION")
	public String getProjectCaption() {
		return projectCaption;
	}

	public void setProjectCaption(String projectCaption) {
		this.projectCaption = projectCaption;
	}

	@Column(name="TREE_CODE")
	@OrderBy
	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	@Column(name="CATEGORY_CAPTION")
	public String getCategoryCaption() {
		return categoryCaption;
	}

	public void setCategoryCaption(String categoryCaption) {
		this.categoryCaption = categoryCaption;
	}

	@Column(name="TEAM_CAPTION")
	public String getTeamCaption() {
		return teamCaption;
	}

	public void setTeamCaption(String teamCaption) {
		this.teamCaption = teamCaption;
	}

	@Column(name="UNIT")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name="METRIC")
	public float getMetric() {
		return metric;
	}

	public void setMetric(float metric) {
		this.metric = metric;
	}

	@Column(name="QUANTITY")
	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	@Transient
	@Column(name="WORKFORCE")
	public float getWorkforce() {
		return quantity * metric;
	}

	@Column(name="APPROVED_MANHOUR")
	public Float getApprovedManhour() {
		return approvedManhour;
	}

	public void setApprovedManhour(Float approvedManhour) {
		this.approvedManhour = approvedManhour;
	}

	@Column(name="MANHOUR_TO_APPROVE")
	public Float getManhourToApprove() {
		return manhourToApprove;
	}

	public void setManhourToApprove(Float manhourToApprove) {
		this.manhourToApprove = manhourToApprove;
	}

}
