package com.nauticana.shipment.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentLineId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "SHIPMENT_ID", nullable = false)
	private int shipmentId;

	@Column(name = "SHIPMENT_LINE", nullable = false)
	private short shipmentLine;

	public ShipmentLineId(String keys) {
		String[] s = keys.split(",");
		this.shipmentId = Integer.parseInt(s[0]);
		this.shipmentLine = Short.parseShort(s[1]);
	}

	@Override
	public String toString() {
		return this.shipmentId + "," + this.shipmentLine;
	}
}
