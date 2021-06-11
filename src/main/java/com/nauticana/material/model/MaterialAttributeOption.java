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
@Table(name = MaterialAttributeOption.TABLE_NAME)
public class MaterialAttributeOption implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "materialAttributeOption";
	public  static final String   TABLE_NAME  = "MATERIAL_ATTRIBUTE_OPTION";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "magId", column = @Column(name = "MAG_ID", nullable = false, length = 8)),
			@AttributeOverride(name = "low", column = @Column(name = "LOW", nullable = false, length = 8)) })
	private MaterialAttributeOptionId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAG_ID", nullable = false, insertable = false, updatable = false)
	private MaterialAttributeGroup materialAttributeGroup;

	@Column(name = "HIGH", length = 8)
	private String high;

	@Column(name = "CAPTION", nullable = false, length = 30)
	private String caption;
}
