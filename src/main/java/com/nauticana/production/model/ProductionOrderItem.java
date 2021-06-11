package com.nauticana.production.model;

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
@Table(name = ProductionOrderItem.TABLE_NAME)
public class ProductionOrderItem implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "productionOrderItem";
	public  static final String   TABLE_NAME  = "PRODUCTION_ORDER_ITEM";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "productionOrderId", column = @Column(name = "PRODUCTION_ORDER_ID", nullable = false)),
			@AttributeOverride(name = "line", column = @Column(name = "LINE", nullable = false)) })
	private ProductionOrderItemId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false)
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTION_ORDER_ID", nullable = false, insertable = false, updatable = false)
	private ProductionOrder productionOrder;

	@Column(name = "QUANTITY", nullable = false, precision = 10)
	private BigDecimal quantity;

	@Column(name = "UNIT", nullable = false, length = 3)
	private String unit;

	@Column(name = "STATUS", nullable = false, length = 1)
	private String status;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productionOrderItem")
	private Set<ProductionLineReason> productionLineReasons = new HashSet<ProductionLineReason>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productionOrderItem")
	private Set<ProductionJob> productionJobs = new HashSet<ProductionJob>(0);
}
