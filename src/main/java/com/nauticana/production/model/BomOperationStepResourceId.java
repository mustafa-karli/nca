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
public class BomOperationStepResourceId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "BOM_OPERATION_ID", nullable = false)
	private int bomOperationId;

	@Column(name = "STEP", nullable = false)
	private short step;

	@Column(name = "RESOURCE_TYPE", nullable = false, length = 8)
	private String resourceType;

	public BomOperationStepResourceId(String keys) {
		String[] s = keys.split(",");
		this.bomOperationId = Integer.parseInt(s[0]);
		this.step = Short.parseShort(s[1]);
		this.resourceType = s[2];
	}

	@Override
	public String toString() {
		return this.bomOperationId + "," + this.step + "," + this.resourceType;
	}
}
