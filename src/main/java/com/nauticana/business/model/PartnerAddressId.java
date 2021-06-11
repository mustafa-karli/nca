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
public class PartnerAddressId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "BUSINESS_PARTNER_ID", nullable = false)
	private int businessPartnerId;

	@Column(name = "ADDRESS_ID", nullable = false)
	private short addressId;

	public PartnerAddressId(String keys) {
		String[] s = keys.split(",");
		this.businessPartnerId = Integer.parseInt(s[0]);
		this.addressId = Short.parseShort(s[1]);
	}

	@Override
	public String toString() {
		return this.businessPartnerId + "," + this.addressId;
	}
}
