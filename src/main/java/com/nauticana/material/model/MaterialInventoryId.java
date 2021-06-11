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
public class MaterialInventoryId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ORGANIZATION_ID", nullable = false)
	private int organizationId;

	@Column(name = "MATERIAL_ID", nullable = false)
	private int materialId;

	public MaterialInventoryId(String keys) {
		String[] s = keys.split(",");
		this.organizationId = Integer.parseInt(s[0]);
		this.materialId = Integer.parseInt(s[1]);
	}

	@Override
	public String toString() {
		return this.organizationId + "," + this.materialId;
	}
}
