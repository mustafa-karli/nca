package com.nauticana.maintenance.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTypeStepId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "SERVICE_TYPE_ID", nullable = false, length = 30)
	private String serviceTypeId;

	@Column(name = "MATERIAL_TYPE_ID", nullable = false, length = 30)
	private String materialTypeId;

	public ServiceTypeStepId(String keys) {
		String[] s = keys.split(",");
		this.serviceTypeId = s[0];
		this.materialTypeId = s[1];
	}

	@Override
	public String toString() {
		return this.serviceTypeId + "," + this.materialTypeId;
	}
}
