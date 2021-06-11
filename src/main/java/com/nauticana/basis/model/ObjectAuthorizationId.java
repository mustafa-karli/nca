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
public class ObjectAuthorizationId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "AUTHORITY_GROUP", nullable = false, length = 30)
	private String authorityGroup;

	@Column(name = "OBJECT_TYPE", nullable = false, length = 30)
	private String objectType;

	@Column(name = "ACTION", nullable = false, length = 30)
	private String action;

	@Column(name = "KEY_VALUE", nullable = false, length = 100)
	private String keyValue;

	public ObjectAuthorizationId(String keys) {
		String[] s = keys.split(",");
		this.authorityGroup = s[0];
		this.objectType = s[1];
		this.action = s[2];
		this.keyValue = s[3];
	}

	@Override
	public String toString() {
		return this.authorityGroup + "," + this.objectType + "," + this.action + "," + this.keyValue;
	}
}
