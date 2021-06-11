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
public class ProductionLineReasonId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PRODUCTION_ORDER_ID", nullable = false)
	private int productionOrderId;

	@Column(name = "LINE", nullable = false)
	private short line;

	@Column(name = "ORDER_TYPE", nullable = false, length = 1)
	private String orderType;

	@Column(name = "ORDER_ID", nullable = false)
	private int orderId;

	@Column(name = "ORDER_LINE", nullable = false)
	private short orderLine;

	public ProductionLineReasonId(String keys) {
		String[] s = keys.split(",");
		this.productionOrderId = Integer.parseInt(s[0]);
		this.line = Short.parseShort(s[1]);
		this.orderType = s[2];
		this.orderId = Integer.parseInt(s[3]);
		this.orderLine = Short.parseShort(s[4]);
	}

	@Override
	public String toString() {
		return this.productionOrderId + "," + this.line + "," + this.orderType + "," + this.orderId + "," + this.orderLine;
	}
}
