package com.nauticana.maintenance.model;

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
import javax.persistence.Table;

import com.nauticana.material.model.MaterialType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = ServiceTypeStep.TABLE_NAME)
public class ServiceTypeStep implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "serviceTypeStep";
	public  static final String   TABLE_NAME  = "SERVICE_TYPE_STEP";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "serviceTypeId", column = @Column(name = "SERVICE_TYPE_ID", nullable = false, length = 30)),
			@AttributeOverride(name = "materialTypeId", column = @Column(name = "MATERIAL_TYPE_ID", nullable = false, length = 30)) })
	private ServiceTypeStepId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_TYPE_ID", nullable = false, insertable = false, updatable = false)
	private MaterialType materialType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_TYPE_ID", nullable = false, insertable = false, updatable = false)
	private ServiceType serviceType;

	@Column(name = "QUANTITY", nullable = false)
	private int quantity;

	@Column(name = "UNIT", nullable = false, length = 3)
	private String unit;

	@Column(name = "DURATION", nullable = false)
	private int duration;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceTypeStep")
	private Set<MxCounterInterval> mxCounterIntervals = new HashSet<MxCounterInterval>(0);
}
