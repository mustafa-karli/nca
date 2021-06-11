package com.nauticana.shipment.model;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nauticana.material.model.Material;

import lombok.Data;

@Data
@Entity
@Table(name = ShipmentLine.TABLE_NAME)
public class ShipmentLine implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "shipmentLine";
	public  static final String   TABLE_NAME  = "SHIPMENT_LINE";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "shipmentId", column = @Column(name = "SHIPMENT_ID", nullable = false)),
			@AttributeOverride(name = "shipmentLine", column = @Column(name = "SHIPMENT_LINE", nullable = false)) })
	private ShipmentLineId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false)
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SHIPMENT_ID", nullable = false, insertable = false, updatable = false)
	private Shipment shipment;

	@Column(name = "QUANTITY", nullable = false, precision = 10)
	private BigDecimal quantity;

	@Column(name = "UNIT", nullable = false, length = 2)
	private String unit;

	@Column(name = "REF_ORDER_TYPE", length = 1)
	private Character refOrderType;

	@Column(name = "REF_ORDER_ID")
	private Integer refOrderId;
}
