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
public class DomainValueId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "DOMAIN", nullable = false, length = 30)
	private String domain;

	@Column(name = "REFVALUE", nullable = false, length = 30)
	private String refvalue;

	public DomainValueId(String keys) {
		String[] s = keys.split(",");
		this.domain = s[0];
		this.refvalue = s[1];
	}
	
	@Override
	public String toString() {
		return this.domain + "," + this.refvalue;
	}
}
