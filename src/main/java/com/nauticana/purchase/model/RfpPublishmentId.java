package com.nauticana.purchase.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RfpPublishmentId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "RFP_ID", nullable = false)
	private int		rfpId;

	@Column(name = "VENDOR_ID", nullable = false)
	private int		vendorId;

	public RfpPublishmentId(String keys) {
		String[] s = keys.split(",");
		this.rfpId = Integer.parseInt(s[0]);
		this.vendorId = Integer.parseInt(s[1]);
	}

	@Override
	public String toString() {
		return this.rfpId + "," + this.vendorId;
	}
}
