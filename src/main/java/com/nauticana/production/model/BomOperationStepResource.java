package com.nauticana.production.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = BomOperationStepResource.TABLE_NAME)
public class BomOperationStepResource implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "bomOperationStepResource";
	public  static final String   TABLE_NAME  = "BOM_OPERATION_STEP_RESOURCE";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bomOperationId", column = @Column(name = "BOM_OPERATION_ID", nullable = false)),
			@AttributeOverride(name = "step", column = @Column(name = "STEP", nullable = false)),
			@AttributeOverride(name = "resourceType", column = @Column(name = "RESOURCE_TYPE", nullable = false, length = 8)) })
	private BomOperationStepResourceId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "BOM_OPERATION_ID", referencedColumnName = "BOM_OPERATION_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "STEP", referencedColumnName = "STEP", nullable = false, insertable = false, updatable = false) })
	private BomOperationStep bomOperationStep;

	@Column(name = "UNIT_DURATION")
	private Integer unitDuration;
}
