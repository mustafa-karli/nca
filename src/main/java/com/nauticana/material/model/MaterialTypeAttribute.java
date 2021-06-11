package com.nauticana.material.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "MATERIAL_TYPE_ATTRIBUTE")
public class MaterialTypeAttribute implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "materialTypeId", column = @Column(name = "MATERIAL_TYPE_ID", nullable = false, length = 30)),
		@AttributeOverride(name = "magId", column = @Column(name = "MAG_ID", nullable = false, length = 8)) })
	private MaterialTypeAttributeId	id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAG_ID", nullable = false, insertable = false, updatable = false)
	private MaterialAttributeGroup			materialAttributeGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_TYPE_ID", nullable = false, insertable = false, updatable = false)
	private MaterialType					materialType;

	@Column(name = "MANDATORY", nullable = false, length = 1)
	private String							mandatory;

	@Column(name = "DEFAULT_VALUE", length = 8)
	private String							defaultValue;
}
