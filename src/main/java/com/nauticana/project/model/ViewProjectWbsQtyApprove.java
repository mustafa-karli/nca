package com.nauticana.project.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

@Entity
@Table(name = "PROJECT_WBS_QTY_TO_APR")
public class ViewProjectWbsQtyApprove {

	public static final String tableName = "PROJECT_WBS_QTY_TO_APR";
	public static final String[] fieldNames = new String[] {"PROJECT_ID", "CATEGORY_ID", "TEAM_ID", "BEGDA", "PROJECT_CAPTION", "TREE_CODE", "CATEGORY_CAPTION", "TEAM_CAPTION", "UNIT", "METRIC", "QUANTITY", "WORKFORCE", "APPROVED_QUANTITY", "ENDDA", "QUANTITY_TO_APPROVE"};
	
	private ProjectWbsQuantityId id;
	private String projectCaption;
	private String treeCode;
	private String categoryCaption;
	private String teamCaption;
	private String unit;
	private float  metric;
	private float  quantity;
	private float  approvedQuantity;
	private Date   endda;
	private float  quantityToApprove;
	
	public ViewProjectWbsQtyApprove() {
		
	}
	
	public ViewProjectWbsQtyApprove(ProjectWbsQuantityId id, String projectCaption, String treeCode, String categoryCaption, String teamCaption, String unit, float metric, float quantity, float approvedQuantity, Date endda, float quantitytoApprove) {
		super();
		this.id = id;
		this.projectCaption = projectCaption;
		this.treeCode = treeCode;
		this.categoryCaption = categoryCaption;
		this.teamCaption = teamCaption;
		this.unit = unit;
		this.metric = metric;
		this.quantity = quantity;
		this.approvedQuantity = approvedQuantity;
		this.endda = endda;
		this.quantityToApprove = quantitytoApprove;
	}

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
		@AttributeOverride(name = "categoryId", column = @Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)),
		@AttributeOverride(name = "teamId", column = @Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)) })
	public ProjectWbsQuantityId getId() {
		return id;
	}

	public void setId(ProjectWbsQuantityId id) {
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

	@Column(name="APPROVED_QUANTITY")
	public float getApprovedQuantity() {
		return approvedQuantity;
	}

	public void setApprovedQuantity(float approvedQuantity) {
		this.approvedQuantity = approvedQuantity;
	}

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "ENDDA", nullable = false)
	public Date getEndda() {
		return endda;
	}

	public void setEndda(Date endda) {
		this.endda = endda;
	}

	@Column(name="QUANTITY_TO_APPROVE")
	public float getQuantityToApprove() {
		return quantityToApprove;
	}

	public void setQuantityToApprove(float quantityToApprove) {
		this.quantityToApprove = quantityToApprove;
	}
}
