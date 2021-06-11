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
public class ProductionResourceId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "RESOURCE_TYPE", nullable = false, length = 8)
	private String resourceType;

	@Column(name = "ORGANIZATION_ID", nullable = false)
	private int organizationId;

	public ProductionResourceId(String keys) {
		String[] s = keys.split(",");
		this.resourceType = s[0];
		this.organizationId = Integer.parseInt(s[1]);
	}

	@Override
	public String toString() {
		return this.resourceType + "," + this.organizationId;
	}
}
