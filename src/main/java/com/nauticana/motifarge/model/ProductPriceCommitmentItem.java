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

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT_PRICE_COMMITMENT_ITEM")
public class ProductPriceCommitmentItem implements java.io.Serializable {

	private static final long               serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)), @AttributeOverride(name = "businessPartnerId", column = @Column(name = "BUSINESS_PARTNER_ID", nullable = false)),
		@AttributeOverride(name = "orderDeadLine", column = @Column(name = "ORDER_DEAD_LINE", nullable = false, length = 26)),
		@AttributeOverride(name = "quantity", column = @Column(name = "QUANTITY", nullable = false, precision = 10)) })
	private ProductPriceCommitmentItemId	id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "BUSINESS_PARTNER_ID", referencedColumnName = "BUSINESS_PARTNER_ID", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "ORDER_DEAD_LINE", referencedColumnName = "ORDER_DEAD_LINE", nullable = false, insertable = false, updatable = false) })
	private ProductPriceCommitment productPriceCommitment;

	@Column(name = "PRICE", nullable = false, precision = 10)
	private BigDecimal price;
}
