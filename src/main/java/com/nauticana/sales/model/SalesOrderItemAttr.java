package com.nauticana.sales.model;

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
@Table(name = SalesOrderItemAttr.TABLE_NAME)
public class SalesOrderItemAttr implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "salesOrderItemAttr";
	public  static final String   TABLE_NAME  = "SALES_ORDER_ITEM_ATTR";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "salesOrderId", column = @Column(name = "SALES_ORDER_ID", nullable = false)),
			@AttributeOverride(name = "line", column = @Column(name = "LINE", nullable = false)),
			@AttributeOverride(name = "magId", column = @Column(name = "MAG_ID", nullable = false, length = 8)) })
	private SalesOrderItemAttrId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAG_ID", nullable = false, insertable = false, updatable = false)
	private MaterialAttributeGroup materialAttributeGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "SALES_ORDER_ID", referencedColumnName = "SALES_ORDER_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "LINE", referencedColumnName = "LINE", nullable = false, insertable = false, updatable = false) })
	private SalesOrderItem salesOrderItem;

	@Column(name = "VALUE", nullable = false, length = 8)
	private String value;
}
