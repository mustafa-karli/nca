package com.nauticana.request.model;

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

import com.nauticana.material.model.Material;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT_BY_DEFINE")
public class ProductByDefine implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "materialTypeId", column = @Column(name = "MATERIAL_TYPE_ID", nullable = false, length = 30)),
		@AttributeOverride(name = "organizationId", column = @Column(name = "ORGANIZATION_ID", nullable = false)), @AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)) })
	private ProductByDefineId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false, insertable = false, updatable = false)
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "MATERIAL_TYPE_ID", referencedColumnName = "MATERIAL_TYPE_ID", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", nullable = false, insertable = false, updatable = false) })
	private ProductTypeByDefine productTypeByDefine;

	@Column(name = "CONTENT_REQUIRED", nullable = false, length = 1)
	private String contentRequired;
}
