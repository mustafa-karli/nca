package com.nauticana.request.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ProductByDefineId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MATERIAL_TYPE_ID", nullable = false, length = 30)
	private String	materialTypeId;

	@Column(name = "ORGANIZATION_ID", nullable = false)
	private int		organizationId;

	@Column(name = "MATERIAL_ID", nullable = false)
	private int		materialId;

	public ProductByDefineId(String keys) {
		String[] s = keys.split(",");
		this.materialTypeId = s[0];
		this.organizationId = Integer.parseInt(s[1]);
		this.materialId = Integer.parseInt(s[2]);
	}

	@Override
	public String toString() {
		return this.materialTypeId + "," + this.organizationId + "," + this.materialId;
	}
}
