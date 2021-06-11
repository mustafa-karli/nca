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
public class EquipmentAttributeId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MATERIAL_ID", nullable = false)
	private int materialId;

	@Column(name = "SERIAL_NO", nullable = false, length = 20)
	private String serialNo;

	@Column(name = "MAG_ID", nullable = false, length = 8)
	private String magId;

	public EquipmentAttributeId(String keys) {
		String[] s = keys.split(",");
		this.materialId = Integer.parseInt(s[0]);
		this.serialNo = s[1];
		this.magId = s[2];
	}

	@Override
	public String toString() {
		return this.materialId + "," + this.serialNo + "," + this.magId;
	}
}
