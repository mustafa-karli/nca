package com.nauticana.maintenance.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class FinalEquipmentPartId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MATERIAL_TYPE_ID", nullable = false, length = 30)
	private String materialTypeId;

	@Column(name = "MATERIAL_ID", nullable = false)
	private int materialId;

	public FinalEquipmentPartId(String keys) {
		String[] s = keys.split(",");
		this.materialTypeId = s[0];
		this.materialId = Integer.parseInt(s[1]);
	}

	@Override
	public String toString() {
		return this.materialTypeId + "," + this.materialId;
	}
}
