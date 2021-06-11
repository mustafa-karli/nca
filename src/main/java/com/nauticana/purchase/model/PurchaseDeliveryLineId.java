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
public class PurchaseDeliveryLineId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "DELIVERY_ID", nullable = false)
	private int deliveryId;

	@Column(name = "PURCHASE_ORDER_ID", nullable = false)
	private int purchaseOrderId;

	@Column(name = "LINE", nullable = false)
	private short line;

	public PurchaseDeliveryLineId(String keys) {
		String[] s = keys.split(",");
		this.deliveryId = Integer.parseInt(s[0]);
		this.purchaseOrderId = Integer.parseInt(s[1]);
		this.line = Short.parseShort(s[2]);
	}

	@Override
	public String toString() {
		return this.deliveryId + "," + this.purchaseOrderId + "," + this.line;
	}
}
