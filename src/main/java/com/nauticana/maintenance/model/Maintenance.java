package com.nauticana.maintenance.model;

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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nauticana.personnel.model.Organization;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = Maintenance.TABLE_NAME)
public class Maintenance implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "maintenance";
	public  static final String   TABLE_NAME  = "MAINTENANCE";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)),
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIAL_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "serviceDate", column = @Column(name = "SERVICE_DATE", nullable = false, length = 26)) })
	private MaintenanceId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "SERIAL_NO", referencedColumnName = "SERIAL_NO", nullable = false, insertable = false, updatable = false) })
	private Equipment equipment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false)
	private Organization organization;

	@Column(name = "LABOR", nullable = false, precision = 12)
	private BigDecimal labor;

	@Column(name = "CHARGE", nullable = false, precision = 12)
	private BigDecimal charge;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "maintenance")
	private Set<MaintenanceCounter> maintenanceCounters = new HashSet<MaintenanceCounter>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "maintenance")
	private Set<MaintenanceStep> maintenanceSteps = new HashSet<MaintenanceStep>(0);
}
