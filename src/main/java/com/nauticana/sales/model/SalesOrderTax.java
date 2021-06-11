package com.nauticana.sales.model;

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

import lombok.Data;

@Data
@Entity
@Table(name = SalesOrderTax.TABLE_NAME)
public class SalesOrderTax implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "salesOrderTax";
	public  static final String   TABLE_NAME  = "SALES_ORDER_TAX";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "salesOrderId", column = @Column(name = "SALES_ORDER_ID", nullable = false)),
			@AttributeOverride(name = "line", column = @Column(name = "LINE", nullable = false)),
			@AttributeOverride(name = "taxId", column = @Column(name = "TAX_ID", nullable = false, length = 8)) })
	private SalesOrderTaxId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "SALES_ORDER_ID", referencedColumnName = "SALES_ORDER_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "LINE", referencedColumnName = "LINE", nullable = false, insertable = false, updatable = false) })
	private SalesOrderItem salesOrderItem;

	@Column(name = "AMOUNT", nullable = false, precision = 10)
	private BigDecimal amount;
}
