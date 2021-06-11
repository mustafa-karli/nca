package com.nauticana.production.model;

import java.math.BigDecimal;
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

import com.nauticana.material.model.Material;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = BomOperationStep.TABLE_NAME)
public class BomOperationStep implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "bomOperationStep";
	public  static final String   TABLE_NAME  = "BOM_OPERATION_STEP";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bomOperationId", column = @Column(name = "BOM_OPERATION_ID", nullable = false)),
			@AttributeOverride(name = "step", column = @Column(name = "STEP", nullable = false)) })
	private BomOperationStepId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BOM_OPERATION_ID", nullable = false, insertable = false, updatable = false)
	private BomOperation bomOperation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false)
	private Material material;

	@Column(name = "LEAD_TIME")
	private Integer leadTime;

	@Column(name = "QUANTITY", precision = 10)
	private BigDecimal quantity;

	@Column(name = "UNIT", length = 3)
	private String unit;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bomOperationStep")
	private Set<BomOperationStepResource> bomOperationStepResources = new HashSet<BomOperationStepResource>(0);
}
