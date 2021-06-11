package com.nauticana.maintenance.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nauticana.material.model.Material;
import com.nauticana.material.model.MaterialType;

import lombok.Data;

@Data
@Entity
@Table(name = FinalEquipmentPart.TABLE_NAME)
public class FinalEquipmentPart implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "finalEquipmentPart";
	public  static final String   TABLE_NAME  = "FINAL_EQUIPMENT_PART";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "materialTypeId", column = @Column(name = "MATERIAL_TYPE_ID", nullable = false, length = 30)),
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)) })
	private FinalEquipmentPartId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false, insertable = false, updatable = false)
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_TYPE_ID", nullable = false, insertable = false, updatable = false)
	private MaterialType materialType;

	@Column(name = "QUANTITY", nullable = false)
	private int quantity;

	@Column(name = "UNIT", nullable = false, length = 3)
	private String unit;
}
