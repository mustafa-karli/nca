package com.nauticana.purchase.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nauticana.material.model.Material;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = PurchaseOrderItem.TABLE_NAME)
public class PurchaseOrderItem implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "purchaseOrderItem";
	public  static final String   TABLE_NAME  = "PURCHASE_ORDER_ITEM";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "purchaseOrderId", column = @Column(name = "PURCHASE_ORDER_ID", nullable = false)),
			@AttributeOverride(name = "line", column = @Column(name = "LINE", nullable = false)) })
	private PurchaseOrderItemId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false)
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PURCHASE_ORDER_ID", nullable = false, insertable = false, updatable = false)
	private PurchaseOrder purchaseOrder;

	@Column(name = "QUANTITY", nullable = false, precision = 10)
	private BigDecimal quantity;

	@Column(name = "UNIT", nullable = false, length = 3)
	private String unit;

	@Column(name = "UNIT_PRICE", precision = 10)
	private BigDecimal unitPrice;

	@Column(name = "DISCOUNT", precision = 10)
	private BigDecimal discount;

	@Column(name = "CURRENCY", length = 3)
	private String currency;

	@Column(name = "STATUS", length = 1)
	private String status;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrderItem")
	private Set<PurchaseOrderItemAttr> purchaseOrderItemAttrs = new HashSet<PurchaseOrderItemAttr>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrderItem")
	private Set<PurchaseOrderTax> purchaseOrderTaxes = new HashSet<PurchaseOrderTax>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrderItem")
	private Set<PurchaseDeliveryLine> purchaseDeliveryLines = new HashSet<PurchaseDeliveryLine>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrderItem")
	private Set<PurchaseReason> purchaseReasons = new HashSet<PurchaseReason>(0);
}
