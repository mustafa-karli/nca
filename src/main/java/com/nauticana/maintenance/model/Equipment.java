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

import com.nauticana.material.model.Material;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = Equipment.TABLE_NAME)
public class Equipment implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "equipment";
	public  static final String   TABLE_NAME  = "EQUIPMENT";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)),
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIAL_NO", nullable = false, length = 20)) })
	private EquipmentId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false, insertable = false, updatable = false)
	private Material material;

	@Column(name = "MODEL_YEAR", nullable = false)
	private short modelYear;

	@Column(name = "USAGE_TYPE", nullable = false, length = 1)
	private String usageType;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipment")
	private Set<EquipmentLocation> equipmentLocations = new HashSet<EquipmentLocation>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipment")
	private Set<Maintenance> maintenances = new HashSet<Maintenance>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipment")
	private Set<EquipmentAttribute> equipmentAttributes = new HashSet<EquipmentAttribute>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipment")
	private Set<EquipmentAssignment> equipmentAssignments = new HashSet<EquipmentAssignment>(0);
}
