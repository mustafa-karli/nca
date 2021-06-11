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
public class MaterialPackUnitId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MATERIAL_ID", nullable = false)
	private int materialId;

	@Column(name = "PACK", nullable = false, length = 8)
	private String pack;

	public MaterialPackUnitId(String keys) {
		String[] s = keys.split(",");
		this.materialId = Integer.parseInt(s[0]);
		this.pack = s[1];
	}

	@Override
	public String toString() {
		return this.materialId + "," + this.pack;
	}
}
