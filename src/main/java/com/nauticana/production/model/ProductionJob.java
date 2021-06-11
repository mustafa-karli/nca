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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nauticana.personnel.model.Organization;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = ProductionJob.TABLE_NAME)
public class ProductionJob implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "productionJob";
	public  static final String   TABLE_NAME  = "PRODUCTION_JOB";
	public  static final String   SEQUENCE_NAME = "PRODUCTION_JOB_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "PRODUCTION_JOB_ID", unique = true, nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BOM_OPERATION_ID", nullable = false)
	private BomOperation bomOperation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false)
	private Organization organization;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PRODUCTION_ORDER_ID", referencedColumnName = "PRODUCTION_ORDER_ID", nullable = false),
			@JoinColumn(name = "LINE", referencedColumnName = "LINE", nullable = false) })
	private ProductionOrderItem productionOrderItem;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productionJob")
	private Set<ProductionJobResource> productionJobResources = new HashSet<ProductionJobResource>(0);
}
