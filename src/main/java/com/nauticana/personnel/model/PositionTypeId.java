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
public class PositionTypeId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "POSITION", nullable = false, length = 20)
	private String position;

	public PositionTypeId(String keys) {
		String[] s = keys.split(",");
		this.ownerId = Integer.parseInt(s[0]);
		this.position = s[1];
	}

	@Override
	public String toString() {
		return this.ownerId + "," + this.position;
	}
}
