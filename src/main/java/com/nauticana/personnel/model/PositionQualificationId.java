package com.nauticana.personnel.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PositionQualificationId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "ORGANIZATION_ID", nullable = false)
	private int organizationId;

	@Column(name = "POSITION", nullable = false, length = 20)
	private String position;

	@Column(name = "QUALIFICATION_ID", nullable = false)
	private int qualificationId;

	public PositionQualificationId(String keys) {
		String[] s = keys.split(",");
		this.ownerId = Integer.parseInt(s[0]);
		this.organizationId = Integer.parseInt(s[1]);
		this.position = s[2];
		this.qualificationId = Integer.parseInt(s[3]);
	}

	@Override
	public String toString() {
		return this.ownerId + "," + this.organizationId + "," + this.position + "," + this.qualificationId;
	}
}
