package com.nauticana.sales.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderTaxId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "SALES_ORDER_ID", nullable = false)
	private int salesOrderId;

	@Column(name = "LINE", nullable = false)
	private short line;

	@Column(name = "TAX_ID", nullable = false, length = 8)
	private String taxId;

	public SalesOrderTaxId(String keys) {
		String[] s = keys.split(",");
		this.salesOrderId = Integer.parseInt(s[0]);
		this.line = Short.parseShort(s[1]);
		this.taxId = s[2];
	}

	@Override
	public String toString() {
		return this.salesOrderId + "," + this.line + "," + this.taxId;
	}
}
