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
public class MaterialGroupAssignmentId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MATERIAL_GROUP_ID", nullable = false)
	private int materialGroupId;

	@Column(name = "MATERIAL_ID", nullable = false)
	private int materialId;

	public MaterialGroupAssignmentId(String keys) {
		String[] s = keys.split(",");
		this.materialGroupId = Integer.parseInt(s[0]);
		this.materialId = Integer.parseInt(s[1]);
	}

	@Override
	public String toString() {
		return this.materialGroupId + "," + this.materialId;
	}
}
