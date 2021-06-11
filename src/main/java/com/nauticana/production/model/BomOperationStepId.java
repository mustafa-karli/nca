package com.nauticana.production.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class BomOperationStepId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "BOM_OPERATION_ID", nullable = false)
	private int bomOperationId;

	@Column(name = "STEP", nullable = false)
	private short step;

	public BomOperationStepId(String keys) {
		String[] s = keys.split(",");
		this.bomOperationId = Integer.parseInt(s[0]);
		this.step = Short.parseShort(s[1]);
	}

	@Override
	public String toString() {
		return this.bomOperationId + "," + this.step;
	}
}
