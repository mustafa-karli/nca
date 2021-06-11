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
@Table(name = MaterialAttribute.TABLE_NAME)
public class MaterialAttribute implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "materialAttribute";
	public  static final String   TABLE_NAME  = "MATERIAL_ATTRIBUTE";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)),
			@AttributeOverride(name = "magId", column = @Column(name = "MAG_ID", nullable = false, length = 8)) })
	private MaterialAttributeId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false, insertable = false, updatable = false)
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAG_ID", nullable = false, insertable = false, updatable = false)
	private MaterialAttributeGroup materialAttributeGroup;

	@Column(name = "VALUE", nullable = false, length = 8)
	private String value;
}
