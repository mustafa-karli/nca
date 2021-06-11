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
@Table(name = MaintenanceCounter.TABLE_NAME)
public class MaintenanceCounter implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "maintenanceCounter";
	public  static final String   TABLE_NAME  = "MAINTENANCE_COUNTER";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)),
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIAL_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "serviceDate", column = @Column(name = "SERVICE_DATE", nullable = false, length = 26)),
			@AttributeOverride(name = "counterType", column = @Column(name = "COUNTER_TYPE", nullable = false, length = 8)) })
	private MaintenanceCounterId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "SERIAL_NO", referencedColumnName = "SERIAL_NO", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "SERVICE_DATE", referencedColumnName = "SERVICE_DATE", nullable = false, insertable = false, updatable = false) })
	private Maintenance maintenance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTER_TYPE", nullable = false, insertable = false, updatable = false)
	private MxCounterType mxCounterType;

	@Column(name = "VALUE", nullable = false)
	private int value;
}
