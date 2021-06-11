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
public class MaterialAttributeOptionId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MAG_ID", nullable = false, length = 8)
	private String magId;

	@Column(name = "LOW", nullable = false, length = 8)
	private String low;

	public MaterialAttributeOptionId(String keys) {
		String[] s = keys.split(",");
		this.magId = s[0];
		this.low = s[1];
	}

	@Override
	public String toString() {
		return this.magId + "," + this.low;
	}
}
