package com.nauticana.production.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nauticana.material.model.Material;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = BomOperation.TABLE_NAME)
public class BomOperation implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "bomOperation";
	public  static final String   TABLE_NAME  = "BOM_OPERATION";
	public  static final String   SEQUENCE_NAME = "BOM_OPERATION_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "BOM_OPERATION_ID", unique = true, nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false)
	private Material material;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String caption;

	@Column(name = "FACILITY_TYPE", nullable = false, length = 8)
	private String facilityType;

	@Column(name = "SETUP_TIME", nullable = false)
	private int setupTime;

	@Column(name = "UNIT_TIME", nullable = false)
	private int unitTime;

	@Column(name = "MAKEUP_TIME", nullable = false)
	private int makeupTime;

	@Column(name = "PRODUCTION_UNIT", nullable = false, length = 3)
	private String productionUnit;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bomOperation")
	private Set<ProductionJob> productionJobs = new HashSet<ProductionJob>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bomOperation")
	private Set<BomOperationStep> bomOperationSteps = new HashSet<BomOperationStep>(0);
}
