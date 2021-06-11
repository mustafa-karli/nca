package com.nauticana.purchase.model;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nauticana.material.model.Material;

import lombok.Data;

@Data
@Entity
@Table(name = PurchaseDeliveryLine.TABLE_NAME)
public class PurchaseDeliveryLine implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "purchaseDeliveryLine";
	public  static final String   TABLE_NAME  = "PURCHASE_DELIVERY_LINE";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "deliveryId", column = @Column(name = "DELIVERY_ID", nullable = false)),
			@AttributeOverride(name = "purchaseOrderId", column = @Column(name = "PURCHASE_ORDER_ID", nullable = false)),
			@AttributeOverride(name = "line", column = @Column(name = "LINE", nullable = false)) })
	private PurchaseDeliveryLineId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false)
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DELIVERY_ID", nullable = false, insertable = false, updatable = false)
	private PurchaseDelivery purchaseDelivery;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PURCHASE_ORDER_ID", referencedColumnName = "PURCHASE_ORDER_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "LINE", referencedColumnName = "LINE", nullable = false, insertable = false, updatable = false) })
	private PurchaseOrderItem purchaseOrderItem;

	@Column(name = "QUANTITY", nullable = false, precision = 10)
	private BigDecimal quantity;

	@Column(name = "UNIT", length = 3)
	private String unit;
}
