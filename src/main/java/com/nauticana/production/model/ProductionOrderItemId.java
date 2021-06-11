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
public class ProductionOrderItemId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PRODUCTION_ORDER_ID", nullable = false)
	private int productionOrderId;

	@Column(name = "LINE", nullable = false)
	private short line;

	public ProductionOrderItemId(String keys) {
		String[] s = keys.split(",");
		this.productionOrderId = Integer.parseInt(s[0]);
		this.line = Short.parseShort(s[0]);
	}

	@Override
	public String toString() {
		return this.productionOrderId + "," + this.line;
	}
}
