package com.nauticana.business.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class VendorBusinessId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "BUSINESS_PARTNER_ID", nullable = false)
	private int businessPartnerId;

	@Column(name = "NODE_ID", nullable = false)
	private int nodeId;

	public VendorBusinessId(String keys) {
		String[] s = keys.split(",");
		this.businessPartnerId = Integer.parseInt(s[0]);
		this.nodeId = Integer.parseInt(s[1]);
	}

	@Override
	public String toString() {
		return this.businessPartnerId + "," + this.nodeId;
	}
}
