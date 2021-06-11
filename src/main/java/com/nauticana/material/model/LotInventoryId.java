package com.nauticana.material.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class LotInventoryId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ORGANIZATION_ID", nullable = false)
	private int organizationId;

	@Column(name = "MATERIAL_ID", nullable = false)
	private int materialId;

	@Column(name = "LOT_ID", nullable = false, length = 10)
	private String lotId;

	public LotInventoryId(String keys) {
		String[] s = keys.split(",");
		this.organizationId = Integer.parseInt(s[0]);
		this.materialId = Integer.parseInt(s[1]);
		this.lotId = s[2];
	}

	@Override
	public String toString() {
		return this.organizationId + "," + this.materialId + "," + this.lotId;
	}
}
