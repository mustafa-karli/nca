package com.nauticana.project.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

@Entity
public class ViewWbsTeamStatus {

	public static final String[] fieldNames = new String[] {"PROJECT_ID", "CATEGORY_ID", "TEAM_ID", "PRJ_CAPTION", "TREE_CODE", "CAT_CAPTION", "TEAM_CAPTION", "UNIT", "METRIC", "QUANTITY", "WORKFORCE", "SUM_QUANTITY", "SUM_MANHOUR", "BEGDA", "ENDDA", "LAST_QUANTITY", "NEXT_BEGDA"};
	
	private ViewWbsTeamStatusId id;
	private String prjCaption;
	private int parentId;
	private String treeCode;
	private String catCaption;
	private String teamCaption;
	private String unit;
	private float  metric;
	private float  quantity;
	private float  sumQuantity;
	private int    sumManhour;
	private Date   begda;
	private Date   endda;
	private float  lastQuantity;
	private Date   nextBegda;



	public ViewWbsTeamStatus(ViewWbsTeamStatusId id, String prjCaption, int parentId, String treeCode, String catCaption, String teamCaption, String unit,
			float metric, float quantity, float sumQuantity, int sumManhour, Date begda, Date endda, float lastQuantity) {
		super();
		this.id = id;
		this.prjCaption = prjCaption;
		this.parentId = parentId;
		this.treeCode = treeCode;
		this.catCaption = catCaption;
		this.teamCaption = teamCaption;
		this.unit = unit;
		this.metric = metric;
		this.quantity = quantity;
		this.sumQuantity = sumQuantity;
		this.sumManhour = sumManhour;
		this.begda = begda;
		this.endda = endda;
		this.lastQuantity = lastQuantity;
		Calendar c = Calendar.getInstance();
		if (endda == null)
			c.setTimeInMillis(System.currentTimeMillis());
		else
			c.setTimeInMillis(endda.getTime());
		c.add(Calendar.DATE, 1);
		this.nextBegda = c.getTime();
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "categoryId", column = @Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "teamId", column = @Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)) })
	public ViewWbsTeamStatusId getId() {
		return id;
	}

	public void setId(ViewWbsTeamStatusId id) {
		this.id = id;
	}

	@Column(name="PARENT_ID")
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
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

	@Column(name="PRJ_CAPTION")
	public String getPrjCaption() {
		return prjCaption;
	}

	public void setPrjCaption(String prjCaption) {
		this.prjCaption = prjCaption;
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
		return this.quantity * this.metric;
	}

	@Column(name="SUM_QUANTITY")
	public float getSumQuantity() {
		return sumQuantity;
	}

	public void setSumQuantity(float sumQuantity) {
		this.sumQuantity = sumQuantity;
	}

	@Column(name="SUM_MANHOUR")
	public int getSumManhour() {
		return sumManhour;
	}

	public void setSumManhour(int sumManhour) {
		this.sumManhour = sumManhour;
	}

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name="BEGDA")
	public Date getBegda() {
		return begda;
	}

	public void setBegda(Date begda) {
		this.begda = begda;
	}

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name="ENDDA")
	public Date getEndda() {
		return endda;
	}

	public void setEndda(Date endda) {
		this.endda = endda;
	}

	@Column(name="LAST_QUANTITY")
	public float getLastQuantity() {
		return lastQuantity;
	}

	public void setLastQuantity(float lastQuantity) {
		this.lastQuantity = lastQuantity;
	}
	
	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name="NEXT_BEGDA")
	public Date getNextBegda() {
		return nextBegda;
	}

	public void setNextBegda(Date nextBegda) {
		this.nextBegda = nextBegda;
	}

	@Override
	public String toString() {
		return id.getProjectId() + "," + id.getCategoryId() + "," + id.getTeamId() + "," + prjCaption + "," + treeCode + "," + catCaption + "," + teamCaption + "," + unit  + "," + metric  + "," + quantity  + "," + getWorkforce()  + "," + sumQuantity + "," + sumManhour + "," + begda + "," + endda + "," + lastQuantity + "," + nextBegda;
	}
}
