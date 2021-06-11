package com.nauticana.production.model;

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

import com.nauticana.personnel.model.Organization;

import lombok.Data;

@Data
@Entity
@Table(name = ProductionResource.TABLE_NAME)
public class ProductionResource implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "productionResource";
	public  static final String   TABLE_NAME  = "PRODUCTION_RESOURCE";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "resourceType", column = @Column(name = "RESOURCE_TYPE", nullable = false, length = 8)),
			@AttributeOverride(name = "organizationId", column = @Column(name = "ORGANIZATION_ID", nullable = false)) })
	private ProductionResourceId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false, insertable = false, updatable = false)
	private Organization organization;

	@Column(name = "QUANTITY", nullable = false)
	private int quantity;

	@Column(name = "UNIT", nullable = false, length = 3)
	private String unit;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productionResource")
	private Set<ProductionJobResource> productionJobResources = new HashSet<ProductionJobResource>(0);
}
