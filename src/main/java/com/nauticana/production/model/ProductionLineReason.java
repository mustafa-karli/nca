package com.nauticana.production.model;

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

import lombok.Data;

@Data
@Entity
@Table(name = ProductionLineReason.TABLE_NAME)
public class ProductionLineReason implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "productionLineReason";
	public  static final String   TABLE_NAME  = "PRODUCTION_LINE_REASON";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "productionOrderId", column = @Column(name = "PRODUCTION_ORDER_ID", nullable = false)),
			@AttributeOverride(name = "line", column = @Column(name = "LINE", nullable = false)),
			@AttributeOverride(name = "orderType", column = @Column(name = "ORDER_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "orderId", column = @Column(name = "ORDER_ID", nullable = false)),
			@AttributeOverride(name = "orderLine", column = @Column(name = "ORDER_LINE", nullable = false)) })
	private ProductionLineReasonId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PRODUCTION_ORDER_ID", referencedColumnName = "PRODUCTION_ORDER_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "LINE", referencedColumnName = "LINE", nullable = false, insertable = false, updatable = false) })
	private ProductionOrderItem productionOrderItem;

	@Column(name = "PRIORITY", nullable = false)
	private short priority;
}
