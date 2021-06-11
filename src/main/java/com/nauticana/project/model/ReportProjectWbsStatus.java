package com.nauticana.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class ReportProjectWbsStatus {

	public static final String[] fieldNames = new String[] { "LOCAL", "CODE", "CAPTION", "CUSTOMER", "CATEGORY_ID", "TREE_CODE", "CAT_CAPTION", "CUSTOMER_WBS_CODE", "CUSTOMER_WBS_CAPTION", "ACCOUNT_SCHEMA_ID", "ACCOUNT_SCHEMA_CAPTION", "UNIT", "METRIC", "QUANTITY", "WORKFORCE", "PUP_METRIC", "PUP_QUANTITY", "PUP_WORKFORCE", "PLANNED_METRIC", "PLANNED_QUANTITY", "PLANNED_WORKFORCE", "SUM_METRIC", "SUM_QUANTITY", "SUM_WORKFORCE", "TERM_METRIC", "TERM_QUANTITY", "TERM_WORKFORCE"};

	private int    categoryId;
	private String treeCode;
	private String catCaption;
	private String customerWbsCode;
	private String customerWbsCaption;
	private Integer accountSchemaId;
	private String  accountSchemaCaption;
	private String unit;
	private float  metric;
	private float  quantity;
	private float  workforce;
	private float  pupMetric;
	private float  pupQuantity;
	private float  pupWorkforce;
	private float  plannedMetric;
	private float  plannedQuantity;
	private float  plannedWorkforce;
	private float  sumMetric;
	private float  sumQuantity;
	private float  sumWorkforce;
	private float  termMetric;
	private float  termQuantity;
	private float  termWorkforce;
	
	public ReportProjectWbsStatus(int categoryId, String treeCode, String catCaption, String customerWbsCode, String customerWbsCaption, Integer accountSchemaId, String accountSchemaCaption, String unit, float metric,
			float quantity, float workforce, float pupMetric, float pupQuantity, float pupWorkforce, float plannedMetric, float plannedQuantity, float plannedWorkforce, float sumMetric,
			float sumQuantity, float sumWorkforce, float termMetric, float termQuantity, float termWorkforce) {
		super();
		this.categoryId = categoryId;
		this.treeCode = treeCode;
		this.catCaption = catCaption;
		this.customerWbsCode = customerWbsCode;
		this.customerWbsCaption = customerWbsCaption;
		this.accountSchemaId = accountSchemaId;
		this.accountSchemaCaption = accountSchemaCaption;
		this.unit = unit;
		this.metric = metric;
		this.quantity = quantity;
		this.workforce = workforce;
		this.pupMetric = pupMetric;
		this.pupQuantity = pupQuantity;
		this.pupWorkforce = pupWorkforce;
		this.plannedMetric = plannedMetric;
		this.plannedQuantity = plannedQuantity;
		this.plannedWorkforce = plannedWorkforce;
		this.sumMetric = sumMetric;
		this.sumQuantity = sumQuantity;
		this.sumWorkforce = sumWorkforce;
		this.termMetric = termMetric;
		this.termQuantity = termQuantity;
		this.termWorkforce = termWorkforce;
	}

	@Id
	@Column(name="CATEGORY_ID")
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name="TREE_CODE")
	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	@Column(name="CAT_CAPTION")
	public String getCatCaption() {
		return catCaption;
	}

	public void setCatCaption(String catCaption) {
		this.catCaption = catCaption;
	}

	@Column(name="CUSTOMER_WBS_CODE")
	public String getCustomerWbsCode() {
		return customerWbsCode;
	}

	public void setCustomerWbsCode(String customerWbsCode) {
		this.customerWbsCode = customerWbsCode;
	}

	@Column(name="CUSTOMER_WBS_CAPTION")
	public String getCustomerWbsCaption() {
		return customerWbsCaption;
	}

	public void setCustomerWbsCaption(String customerWbsCaption) {
		this.customerWbsCaption = customerWbsCaption;
	}

	@Column(name="ACCOUNT_SCHEMA_ID")
	public Integer getAccountSchemaId() {
		return accountSchemaId;
	}

	public void setAccountSchemaId(Integer accountSchemaId) {
		this.accountSchemaId = accountSchemaId;
	}

	@Column(name="ACCOUNT_SCHEMA_CAPTION")
	public String getAccountSchemaCaption() {
		return accountSchemaCaption;
	}

	public void setAccountSchemaCaption(String accountSchemaCaption) {
		this.accountSchemaCaption = accountSchemaCaption;
	}

	@Column(name="UNIT")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name="METRIC", precision = 8, scale = 2)
	public float getMetric() {
		return metric;
	}

	public void setMetric(float metric) {
		this.metric = metric;
	}

	@Column(name="QUANTITY", precision = 8, scale = 2)
	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	@Transient
	public float getWorkforce() {
		return workforce;
	}

	public void setWorkforce(float workforce) {
		this.workforce = workforce;
	}

	@Column(name="PUP_METRIC", precision = 8, scale = 2)
	public float getPupMetric() {
		return pupMetric;
	}

	public void setPupMetric(float pupMetric) {
		this.pupMetric = pupMetric;
	}

	@Column(name="PUP_QUANTITY", precision = 8, scale = 2)
	public float getPupQuantity() {
		return pupQuantity;
	}

	public void setPupQuantity(float pupQuantity) {
		this.pupQuantity = pupQuantity;
	}

	@Transient
	public float getPupWorkforce() {
		return pupWorkforce;
	}

	public void setPupWorkforce(float pupWorkforce) {
		this.pupWorkforce = pupWorkforce;
	}

	@Column(name="PLANNED_METRIC", precision = 8, scale = 2)
	public float getPlannedMetric() {
		return plannedMetric;
	}

	public void setPlannedMetric(float plannedMetric) {
		this.plannedMetric = plannedMetric;
	}

	@Column(name="PLANNED_QUANTITY", precision = 8, scale = 2)
	public float getPlannedQuantity() {
		return plannedQuantity;
	}

	public void setPlannedQuantity(float plannedQuantity) {
		this.plannedQuantity = plannedQuantity;
	}

	@Transient
	public float getPlannedWorkforce() {
		return plannedWorkforce;
	}

	public void setPlannedWorkforce(float plannedWorkforce) {
		this.plannedWorkforce = plannedWorkforce;
	}

	@Transient
	public float getSumMetric() {
		return sumMetric;
	}

	public void setSumMetric(float sumMetric) {
		this.sumMetric = sumMetric;
	}

	@Column(name="SUM_QUANTITY", precision = 8, scale = 2)
	public float getSumQuantity() {
		return sumQuantity;
	}

	public void setSumQuantity(float sumQuantity) {
		this.sumQuantity = sumQuantity;
	}

	@Column(name="SUM_WORKFOCE", precision = 8, scale = 2)
	public float getSumWorkforce() {
		return sumWorkforce;
	}

	public void setSumWorkforce(float sumWorkforce) {
		this.sumWorkforce = sumWorkforce;
	}

	@Transient
	public float getTermMetric() {
		return termMetric;
	}

	public void setTermMetric(float termMetric) {
		this.termMetric = termMetric;
	}

	@Column(name="TERM_QUANTITY", precision = 8, scale = 2)
	public float getTermQuantity() {
		return termQuantity;
	}

	public void setTermQuantity(float termQuantity) {
		this.termQuantity = termQuantity;
	}

	@Column(name="TERM_WORKFORCE", precision = 8, scale = 2)
	public float getTermWorkforce() {
		return termWorkforce;
	}

	public void setTermWorkforce(float termWorkforce) {
		this.termWorkforce = termWorkforce;
	}
	
	
}
