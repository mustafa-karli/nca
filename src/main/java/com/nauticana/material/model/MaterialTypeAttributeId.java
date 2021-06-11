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
public class MaterialTypeAttributeId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MATERIAL_TYPE_ID", nullable = false, length = 30)
	private String	materialTypeId;

	@Column(name = "MAG_ID", nullable = false, length = 8)
	private String	magId;

	public MaterialTypeAttributeId(String keys) {
		String[] s = keys.split(",");
		this.materialTypeId = s[0];
		this.magId = s[1];
	}

	@Override
	public String toString() {
		return this.materialTypeId + "," + this.magId;
	}
}
