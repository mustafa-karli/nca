package com.nauticana.project.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class ViewProgressBrief {

	public static final String[] fieldNames = new String[] {"CATEGORY_ID", "PARENT_ID", "TREE_CODE", "CAPTION", "PROJECT_ID", "UNIT", "METRIC", "QUANTITY", "WORKFORCE", "PUP_METRIC", "PUP_QUANTITY", "PUP_WORKFORCE", "PLANNED_METRIC", "PLANNED_QUANTITY", "PLANNED_WORKFORCE", "SUM_QUANTITY", "SUM_MANHOUR", "SUM_METRIC", "PROGRESS", "EARNED_MANHOUR", "PERFORMANCE", "PERFORMANCE_MULTIPLIER", "ESTIMATED_MANHOUR", "REMAINING_COMPLETION_MANHOUR", "DEVIATION"};
	
	private int    categoryId;
	private int    parentId;
	private String treeCode;
	private String caption;
	private String unit;
	private float  metric;
	private float  quantity;
	private float  workforce;
	private float  pupMetric;
	private float  pupQuantity;
	private float  pupWorkforce;
	private float  sumQuantity;
	private float  plannedMetric;
	private float  plannedQuantity;
	private float  plannedWorkforce;
	private int    sumManhour;
	
	// transient values

	private float sumMetric;
	private float progress;
	private float earnedManhour;
	private float performans;
	private float performansMultip;
	private float estimatedCompMH;
	private float remainingMH;
	private float deviation;
	private ArrayList<ViewProgressBrief> children;
	private ViewProgressBrief parent;
	
	public ViewProgressBrief(int categoryId, int parentId, String treeCode, String caption, String unit, float metric, float quantity, float workforce, float pupMetric, float pupQuantity, float pupWorkforce, float plannedMetric, float plannedQuantity, float plannedWorkforce, float sumQuantity, int sumManhour) {
		super();
		this.categoryId = categoryId;
		try {this.parentId = parentId;}catch (Exception e) {this.parentId = -1;}
		this.treeCode = treeCode;
		this.caption = caption;
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
		this.sumQuantity = sumQuantity;
		this.sumManhour = sumManhour;

		if (sumQuantity == 0)
			sumMetric = 0;
		else
			sumMetric = sumManhour/sumQuantity;

		if(quantity == 0 )
			this.progress=0;
		else
			this.progress= sumQuantity/pupQuantity;

		this.earnedManhour = this.progress * this.pupWorkforce;

		if (this.sumManhour == 0)
			this.performans = 0;
		else
			this.performans = this.earnedManhour / this.sumManhour;

		if (this.performans == 0)
			this.performansMultip = 0;
		else
			this.performansMultip = 1 / this.performans;

		this.estimatedCompMH = this.performansMultip * this.pupWorkforce;

		this.remainingMH = this.estimatedCompMH - this.sumManhour;

		if (this.pupWorkforce == 0) this.deviation = 0;
		else this.deviation = 1 - this.estimatedCompMH / this.pupWorkforce;

		this.children = new ArrayList<ViewProgressBrief>();
		this.parent = null;
	}

	@Id
	@Column(name="CATEGORY_ID")
	public int getId() {
		return categoryId;
	}

	public void setId(int id) {
		this.categoryId = id;
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

	@Column(name="CAPTION")
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
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

	@Column(name="WORKFORCE")
	public float getWorkforce() {
		return this.workforce;
	}
	
	public void setWorkforce(float workforce) {
		this.workforce = workforce;
	}

	@Column(name="PUP_QUANTITY")
	public float getPupQuantity() {
		return pupQuantity;
	}

	public void setPupQuantity(float pupQuantity) {
		this.pupQuantity = pupQuantity;
	}

	@Column(name="PUP_METRIC")
	public float getPupMetric() {
		return pupMetric;
	}

	public void setPupMetric(float pupMetric) {
		this.pupMetric = pupMetric;
	}

	@Column(name="PUP_WORKFORCE")
	public float getPupWorkforce() {
		return this.pupWorkforce;
	}
	
	public void setPupWorkforce(float pupWorkforce) {
		this.pupWorkforce = pupWorkforce;
	}

	@Column(name="PLANNED_QUANTITY")
	public float getPlannedQuantity() {
		return plannedQuantity;
	}

	public void setPlannedQuantity(float plannedQuantity) {
		this.plannedQuantity = plannedQuantity;
	}

	@Column(name="PLANNED_METRIC")
	public float getPlannedMetric() {
		return plannedMetric;
	}

	public void setPlannedMetric(float plannedMetric) {
		this.plannedMetric = plannedMetric;
	}

	@Column(name="PLANNED_WORKFORCE")
	public float getPlannedWorkforce() {
		return this.plannedWorkforce;
	}
	
	public void setPlannedWorkforce(float plannedWorkforce) {
		this.plannedWorkforce = plannedWorkforce;
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

	@Transient
	public float getSumMetric() {
		return this.sumMetric;
	}
	
	@Transient
	public float getProgress() {
		return progress;
	}

	@Transient
	public float getEarnedManhour() {
		return earnedManhour;
	}

	@Transient
	public float getPerformans() {
		return performans;
	}
	@Transient
	public float getPerformansMultip() {
		return performansMultip;
	}
	@Transient
	public float getEstimatedCompMH() {
		return estimatedCompMH;
	}
	@Transient
	public float getRemainingMH() {
		return remainingMH;
	}
	@Transient
	public float getDeviation() {
		return deviation;
	}
	@Transient
	public ArrayList<ViewProgressBrief> getChildren() {
		return children;
	}
	public void addChild(ViewProgressBrief child) {
		this.children.add(child);
	}
	@Transient
	public ViewProgressBrief getParent() {
		return parent;
	}
	public void setParent(ViewProgressBrief parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return getId() + "," + treeCode + "," + caption + "," + unit  + "," + metric  + "," + quantity  + "," + sumQuantity + "," + sumManhour;// + "," + begda + "," + endda + "," + lastQuantity + "," + nextBegda;
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}
	
	public void setEarnedManhour(float earnedManhour) {
		this.earnedManhour = earnedManhour;
	}

	public void setPerformans(float performans) {
		this.performans = performans;
	}

	public void setEstimatedCompMH(float estimatedCompMH) {
		this.estimatedCompMH = estimatedCompMH;
	}

	public void setRemainingMH(float remainingMH) {
		this.remainingMH = remainingMH;
	}

	public void setDeviation(float deviation) {
		this.deviation = deviation;
	}
	
}
