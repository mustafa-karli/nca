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
public class EquipmentLocationId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MATERIAL_ID", nullable = false)
	private int materialId;

	@Column(name = "SERIAL_NO", nullable = false, length = 20)
	private String serialNo;

	@Column(name = "ORGANIZATION_ID", nullable = false)
	private int organizationId;

	public EquipmentLocationId(String keys) {
		String[] s = keys.split(",");
		this.materialId = Integer.parseInt(s[0]);
		this.serialNo = s[1];
		this.organizationId = Integer.parseInt(s[2]);
	}

	@Override
	public String toString() {
		return this.materialId + "," + this.serialNo + "," + this.organizationId;
	}
}
