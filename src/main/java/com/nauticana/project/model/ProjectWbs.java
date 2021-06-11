package com.nauticana.project.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.nauticana.finance.model.AccountSchema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = ProjectWbs.TABLE_NAME)
public class ProjectWbs implements java.io.Serializable {

	public static final String   ROOTMAPPING = "projectWbs";
	public static final String   TABLE_NAME  = "PROJECT_WBS";
	public static final String[] actions = new String[] { "APPROVE_QUANTITY" };

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "categoryId", column = @Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)) })
	private ProjectWbsId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID", nullable = false, insertable = false, updatable = false)
	private Project project;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID", nullable = false, insertable = false, updatable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_SCHEMA_ID", nullable = true, insertable = true, updatable = true)
	private AccountSchema accountSchema;

	@Column(name = "UNIT", nullable = false, length = 3)
	private String unit;

	@Column(name = "METRIC", nullable = false, precision = 8, scale = 2)
	private float metric;

	@Column(name = "QUANTITY", nullable = false, precision = 8, scale = 2)
	private float quantity;

	@Column(name = "PUP_METRIC", precision = 8, scale = 2)
	private Float pupMetric;

	@Column(name = "PUP_QUANTITY", precision = 8, scale = 2)
	private Float pupQuantity;

	@Column(name = "PLANNED_METRIC", precision = 8, scale = 2)
	private Float plannedMetric;
	
	@Column(name = "PLANNED_QUANTITY", precision = 8, scale = 2)
	private Float plannedQuantity;

	@Column(name = "CUSTOMER_WBS_CODE")
	private String customerWbsCode;

	@Column(name = "CUSTOMER_WBS_CAPTION")
	private String customerWbsCaption;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectWbs")
	private Set<ProjectWbsManhour> projectWbsManhours = new HashSet<ProjectWbsManhour>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OrderBy("BEGDA DESC")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectWbs")
	private Set<ProjectWbsQuantity> projectWbsQuantities = new HashSet<ProjectWbsQuantity>(0);

	@Transient
	@Column(name = "WORKFORCE", precision = 8, scale = 2)
	public float getWorkforce() {
		return quantity * metric;
	}

	@Transient
	@Column(name = "PUP_WORKFORCE", precision = 8, scale = 2)
	public Float getPupWorkforce() {
		try {return pupQuantity * pupMetric;} catch (Exception e) { return null;}
	}

	@Transient
	@Column(name = "PLANNED_WORKFORCE", precision = 8, scale = 2)
	public Float getPlannedWorkforce() {
		try {return plannedQuantity * plannedMetric;} catch (Exception e) { return null;}
	}

}
