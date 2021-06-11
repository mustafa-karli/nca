package com.nauticana.maintenance.model;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;
import com.nauticana.material.model.MaterialType;

import lombok.Data;

@Data
@Entity
@Table(name = MaintenanceStep.TABLE_NAME)
public class MaintenanceStep implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "maintenanceStep";
	public  static final String   TABLE_NAME  = "MAINTENANCE_STEP";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)),
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIAL_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "serviceDate", column = @Column(name = "SERVICE_DATE", nullable = false, length = 26)),
			@AttributeOverride(name = "materialTypeId", column = @Column(name = "MATERIAL_TYPE_ID", nullable = false, length = 30)) })
	private MaintenanceStepId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "SERIAL_NO", referencedColumnName = "SERIAL_NO", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "SERVICE_DATE", referencedColumnName = "SERVICE_DATE", nullable = false, insertable = false, updatable = false) })
	private Maintenance maintenance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_TYPE_ID", nullable = false, insertable = false, updatable = false)
	private MaterialType materialType;

	@Column(name = "QUANTITY", nullable = false)
	private int quantity;

	@Column(name = "UNIT", nullable = false, length = 3)
	private String unit;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTION_TIME", nullable = false, length = 26)
	private Date actionTime;

	@Column(name = "COST", nullable = false, precision = 12)
	private BigDecimal cost;
}
