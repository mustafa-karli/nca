package com.nauticana.purchase.model;

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

import com.nauticana.material.model.MaterialAttributeGroup;

import lombok.Data;

@Data
@Entity
@Table(name = PurchaseOrderItemAttr.TABLE_NAME)
public class PurchaseOrderItemAttr implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "purchaseOrderItemAttr";
	public  static final String   TABLE_NAME  = "PURCHASE_ORDER_ITEM_ATTR";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "purchaseOrderId", column = @Column(name = "PURCHASE_ORDER_ID", nullable = false)),
			@AttributeOverride(name = "line", column = @Column(name = "LINE", nullable = false)),
			@AttributeOverride(name = "magId", column = @Column(name = "MAG_ID", nullable = false, length = 8)) })
	private PurchaseOrderItemAttrId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAG_ID", nullable = false, insertable = false, updatable = false)
	private MaterialAttributeGroup materialAttributeGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PURCHASE_ORDER_ID", referencedColumnName = "PURCHASE_ORDER_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "LINE", referencedColumnName = "LINE", nullable = false, insertable = false, updatable = false) })
	private PurchaseOrderItem purchaseOrderItem;

	@Column(name = "VALUE", length = 8)
	private String value;
}
