package com.nauticana.basis.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UnitConversionId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "SOURCE", nullable = false, length = 2)
	private String source;

	@Column(name = "TARGET", nullable = false, length = 2)
	private String target;

	public UnitConversionId(String keys) {
		String[] s = keys.split(",");
		this.source = s[0];
		this.target = s[1];
	}

	@Override
	public String toString() {
		return this.source + "," + this.target;
	}

}
