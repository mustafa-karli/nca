package com.nauticana.motifarge.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;
import com.nauticana.business.model.Vendor;
import com.nauticana.material.model.Material;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "PRODUCT_PRICE_COMMITMENT")
public class ProductPriceCommitment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)),
			@AttributeOverride(name = "businessPartnerId", column = @Column(name = "BUSINESS_PARTNER_ID", nullable = false)),
			@AttributeOverride(name = "orderDeadLine", column = @Column(name = "ORDER_DEAD_LINE", nullable = false, length = 26)) })
	private ProductPriceCommitmentId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false, insertable = false, updatable = false)
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUSINESS_PARTNER_ID", nullable = false, insertable = false, updatable = false)
	private Vendor vendor;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DELIVERY_PROMISE", nullable = false, length = 26)
	private Date deliveryPromise;

	@Column(name = "MIN_QUANTITY", nullable = false)
	private int minQuantity;

	@Column(name = "MAX_QUANTITY", nullable = false)
	private int maxQuantity;

	@Column(name = "BOX_QUANTITY", nullable = false)
	private int boxQuantity;

	@Column(name = "UNIT", nullable = false, length = 3)
	private String unit;

	@Column(name = "START_PRICE", nullable = false, precision = 12)
	private BigDecimal startPrice;

	@Column(name = "GOAL_PRICE", nullable = false, precision = 12)
	private BigDecimal goalPrice;

	@Column(name = "CURRENCY", nullable = false, length = 3)
	private String currency;

	@Column(name = "PRICING", nullable = false, length = 1)
	private String pricing;

	@Column(name = "CURRENT_ORDER", nullable = false)
	private int currentOrder;

	@Column(name = "CURRENT_PRICE", nullable = false, precision = 12)
	private BigDecimal currentPrice;

	@Column(name = "EARLY_BIRD_PCT", nullable = false, precision = 3)
	private float earlyBirdPct;

	@Column(name = "STATUS", nullable = false, length = 1)
	private String status;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productPriceCommitment")
	@OrderBy
	private Set<ProductPriceCommitmentItem> productPriceCommitmentItems = new LinkedHashSet<ProductPriceCommitmentItem>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productPriceCommitment")
	private Set<SalesOrderCommitment> salesOrderCommitments = new HashSet<SalesOrderCommitment>(0);
}
