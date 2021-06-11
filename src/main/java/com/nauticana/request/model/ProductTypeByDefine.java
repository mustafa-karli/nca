package com.nauticana.request.model;

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

import com.nauticana.material.model.MaterialType;
import com.nauticana.personnel.model.Organization;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "PRODUCT_TYPE_BY_DEFINE")
public class ProductTypeByDefine implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "materialTypeId", column = @Column(name = "MATERIAL_TYPE_ID", nullable = false, length = 30)),
		@AttributeOverride(name = "organizationId", column = @Column(name = "ORGANIZATION_ID", nullable = false)) })
	private ProductTypeByDefineId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_TYPE_ID", nullable = false, insertable = false, updatable = false)
	private MaterialType materialType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false, insertable = false, updatable = false)
	private Organization organization;

	@Column(name = "LEAD_TIME", nullable = false)
	private short leadTime;

	@Column(name = "TIME_TYPE", nullable = false, length = 1)
	private String timeType;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productTypeByDefine")
	private Set<ProductByDefine> productByDefines = new HashSet<ProductByDefine>(0);
}
