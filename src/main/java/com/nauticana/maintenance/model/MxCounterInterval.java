package com.nauticana.maintenance.model;

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
@Table(name = MxCounterInterval.TABLE_NAME)
public class MxCounterInterval implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "mxCounterInterval";
	public  static final String   TABLE_NAME  = "MX_COUNTER_INTERVAL";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "serviceTypeId", column = @Column(name = "SERVICE_TYPE_ID", nullable = false, length = 30)),
			@AttributeOverride(name = "materialTypeId", column = @Column(name = "MATERIAL_TYPE_ID", nullable = false, length = 30)),
			@AttributeOverride(name = "counterType", column = @Column(name = "COUNTER_TYPE", nullable = false, length = 8)) })
	private MxCounterIntervalId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTER_TYPE", nullable = false, insertable = false, updatable = false)
	private MxCounterType mxCounterType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "SERVICE_TYPE_ID", referencedColumnName = "SERVICE_TYPE_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "MATERIAL_TYPE_ID", referencedColumnName = "MATERIAL_TYPE_ID", nullable = false, insertable = false, updatable = false) })
	private ServiceTypeStep serviceTypeStep;

	@Column(name = "INTERVAL", nullable = false)
	private int interval;
}
