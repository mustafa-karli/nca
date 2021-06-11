package com.nauticana.motifarge.model;

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

import com.nauticana.sales.model.SalesOrderItem;

import lombok.Data;

@Data
@Entity
@Table(name = "SALES_ORDER_COMMITMENT")
public class SalesOrderCommitment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)), @AttributeOverride(name = "businessPartnerId", column = @Column(name = "BUSINESS_PARTNER_ID", nullable = false)),
		@AttributeOverride(name = "orderDeadLine", column = @Column(name = "ORDER_DEAD_LINE", nullable = false, length = 26)), @AttributeOverride(name = "salesOrderId", column = @Column(name = "SALES_ORDER_ID", nullable = false)),
		@AttributeOverride(name = "line", column = @Column(name = "LINE", nullable = false)), @AttributeOverride(name = "initialSequence", column = @Column(name = "INITIAL_SEQUENCE", nullable = false)) })
	private SalesOrderCommitmentId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "BUSINESS_PARTNER_ID", referencedColumnName = "BUSINESS_PARTNER_ID", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "ORDER_DEAD_LINE", referencedColumnName = "ORDER_DEAD_LINE", nullable = false, insertable = false, updatable = false) })
	private ProductPriceCommitment productPriceCommitment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "SALES_ORDER_ID", referencedColumnName = "SALES_ORDER_ID", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "LINE", referencedColumnName = "LINE", nullable = false, insertable = false, updatable = false) })
	private SalesOrderItem salesOrderItem;

	@Column(name = "INITIAL_PRICE", nullable = false, precision = 12)
	private BigDecimal initialPrice;

	@Column(name = "ORDER_REFUND", nullable = false, precision = 12)
	private BigDecimal orderRefund;

	@Column(name = "REFUND_DECISION", nullable = false, length = 1)
	private String refundDecision;

	@Column(name = "REFUND_STATUS", nullable = false, length = 1)
	private String refundStatus;
}
