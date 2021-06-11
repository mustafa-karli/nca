package com.nauticana.production.model;

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

import lombok.Data;

@Data
@Entity
@Table(name = ProductionJobResource.TABLE_NAME)
public class ProductionJobResource implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "productionJobResource";
	public  static final String   TABLE_NAME  = "PRODUCTION_JOB_RESOURCE";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "productionJobId", column = @Column(name = "PRODUCTION_JOB_ID", nullable = false)),
			@AttributeOverride(name = "resourceType", column = @Column(name = "RESOURCE_TYPE", nullable = false, length = 8)),
			@AttributeOverride(name = "organizationId", column = @Column(name = "ORGANIZATION_ID", nullable = false)) })
	private ProductionJobResourceId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTION_JOB_ID", nullable = false, insertable = false, updatable = false)
	private ProductionJob productionJob;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "RESOURCE_TYPE", referencedColumnName = "RESOURCE_TYPE", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", nullable = false, insertable = false, updatable = false) })
	private ProductionResource productionResource;

	@Column(name = "QUANTITY")
	private Integer quantity;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGDA", length = 26)
	private Date begda;

	@Column(name = "DURATION")
	private Integer duration;
}
