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
public class PurchaseOrderTaxId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PURCHASE_ORDER_ID", nullable = false)
	private int purchaseOrderId;

	@Column(name = "LINE", nullable = false)
	private short line;

	@Column(name = "TAX_ID", nullable = false, length = 8)
	private String taxId;

	public PurchaseOrderTaxId(String keys) {
		String[] s = keys.split(",");
		this.purchaseOrderId = Integer.parseInt(s[0]);
		this.line = Short.parseShort(s[1]);
		this.taxId = s[2];
	}

	@Override
	public String toString() {
		return this.purchaseOrderId + "," + this.line + "," + this.taxId;
	}
}
