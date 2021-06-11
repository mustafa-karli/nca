package com.nauticana.sales.model;

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
import com.nauticana.motifarge.model.SalesOrderCommitment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "SALES_ORDER_ITEM", schema = "PUBLIC", catalog = "PUBLIC")
public class SalesOrderItem implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "salesOrderId", column = @Column(name = "SALES_ORDER_ID", nullable = false)),
			@AttributeOverride(name = "line", column = @Column(name = "LINE", nullable = false)) })
	private SalesOrderItemId			id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false)
	private Material					material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SALES_ORDER_ID", nullable = false, insertable = false, updatable = false)
	private SalesOrder					salesOrder;

	@Column(name = "QUANTITY", nullable = false, precision = 10)
	private BigDecimal					quantity;

	@Column(name = "UNIT", nullable = false, length = 3)
	private String						unit;

	@Column(name = "UNIT_PRICE", precision = 10)
	private BigDecimal					unitPrice;

	@Column(name = "DISCOUNT", precision = 10)
	private BigDecimal					discount;

	@Column(name = "CURRENCY", length = 3)
	private String						currency;

	@Column(name = "STATUS", length = 1)
	private Character					status;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "salesOrderItem")
	private Set<SalesOrderItemAttr>		salesOrderItemAttrs		= new HashSet<SalesOrderItemAttr>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "salesOrderItem")
	private Set<SalesOrderTax>			salesOrderTaxes			= new HashSet<SalesOrderTax>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "salesOrderItem")
	private Set<SalesOrderCommitment>	salesOrderCommitments	= new HashSet<SalesOrderCommitment>(0);
}
