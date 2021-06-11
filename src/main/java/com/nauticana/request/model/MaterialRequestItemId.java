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
public class MaterialRequestItemId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MATERIAL_REQUEST_ID", nullable = false)
	private int materialRequestId;

	@Column(name = "LINE", nullable = false)
	private short line;

	public MaterialRequestItemId(String keys) {
		String[] s = keys.split(",");
		this.materialRequestId = Integer.parseInt(s[0]);
		this.line = Short.parseShort(s[1]);
	}

	@Override
	public String toString() {
		return this.materialRequestId + "," + this.line;
	}
}
