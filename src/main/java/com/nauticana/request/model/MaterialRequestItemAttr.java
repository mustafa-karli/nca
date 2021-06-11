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

import com.nauticana.material.model.MaterialAttributeGroup;

import lombok.Data;

@Data
@Entity
@Table(name = "MATERIAL_REQUEST_ITEM_ATTR")
public class MaterialRequestItemAttr implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "materialRequestId", column = @Column(name = "MATERIAL_REQUEST_ID", nullable = false)),
			@AttributeOverride(name = "line", column = @Column(name = "LINE", nullable = false)),
			@AttributeOverride(name = "magId", column = @Column(name = "MAG_ID", nullable = false, length = 8)) })
	private MaterialRequestItemAttrId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAG_ID", nullable = false, insertable = false, updatable = false)
	private MaterialAttributeGroup materialAttributeGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "MATERIAL_REQUEST_ID", referencedColumnName = "MATERIAL_REQUEST_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "LINE", referencedColumnName = "LINE", nullable = false, insertable = false, updatable = false) })
	private MaterialRequestItem materialRequestItem;

	@Column(name = "VALUE", nullable = false, length = 8)
	private String value;
}
