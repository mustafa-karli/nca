package com.nauticana.personnel.model;

import java.math.BigDecimal;

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
@Table(name = PositionQualification.TABLE_NAME)
public class PositionQualification implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "positionQualification";
	public  static final String   TABLE_NAME  = "POSITION_QUALIFICATION";

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "ownerId", column = @Column(name = "OWNER_ID", nullable = false)),
		@AttributeOverride(name = "organizationId", column = @Column(name = "ORGANIZATION_ID", nullable = false)),
		@AttributeOverride(name = "position", column = @Column(name = "POSITION", nullable = false, length = 20)),
		@AttributeOverride(name = "qualificationId", column = @Column(name = "QUALIFICATION_ID", nullable = false)) })
	private PositionQualificationId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "OWNER_ID", referencedColumnName = "OWNER_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "POSITION", referencedColumnName = "POSITION", nullable = false, insertable = false, updatable = false) })
	private Position position;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUALIFICATION_ID", nullable = false, insertable = false, updatable = false)
	private Qualification qualification;

	@Column(name = "GRADE", nullable = false, precision = 10)
	private BigDecimal grade;

	@Column(name = "PRIORITY", nullable = false, length = 1)
	private String priority;

	@Column(name = "IMPACT_PERCENT", nullable = false)
	private short impactPercent;
}
