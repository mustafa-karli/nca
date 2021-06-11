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
public class PurchaseReasonId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PURCHASE_ORDER_ID", nullable = false)
	private int purchaseOrderId;

	@Column(name = "LINE", nullable = false)
	private short line;

	@Column(name = "REASON_TYPE", nullable = false, length = 1)
	private String reasonType;

	@Column(name = "REASON_ID", nullable = false)
	private int reasonId;

	@Column(name = "REASON_LINE", nullable = false)
	private short reasonLine;

	public PurchaseReasonId(String keys) {
		String[] s = keys.split(",");
		this.purchaseOrderId = Integer.parseInt(s[0]);
		this.line = Short.parseShort(s[1]);
		this.reasonType = s[2];
		this.reasonId = Integer.parseInt(s[3]);
		this.reasonLine = Short.parseShort(s[4]);
	}

	@Override
	public String toString() {
		return this.purchaseOrderId + "," + this.line + "," + this.reasonType + "," + this.reasonId + "," + this.reasonLine;
	}
}
