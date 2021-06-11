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
public class ProductionJobResourceId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PRODUCTION_JOB_ID", nullable = false)
	private int productionJobId;

	@Column(name = "RESOURCE_TYPE", nullable = false, length = 8)
	private String resourceType;

	@Column(name = "ORGANIZATION_ID", nullable = false)
	private int organizationId;

	public ProductionJobResourceId(String keys) {
		String[] s = keys.split(",");
		this.productionJobId = Integer.parseInt(s[0]);
		this.resourceType = s[1];
		this.organizationId = Integer.parseInt(s[2]);
	}

	@Override
	public String toString() {
		return this.productionJobId + "," + this.resourceType + "," + this.organizationId;
	}
}
