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
public class AuthorityObjectActionId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "OBJECT_TYPE", nullable = false, length = 30)
	private String objectType;

	@Column(name = "ACTION", nullable = false, length = 30)
	private String action;
	
	public AuthorityObjectActionId(String keys) {
		String[] s = keys.split(",");
		this.objectType = s[0];
		this.action = s[1];
	}

	@Override
	public String toString() {
		return this.objectType + "," + this.action;
	}

}
