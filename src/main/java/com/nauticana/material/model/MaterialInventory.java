package com.nauticana.material.model;

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

import com.nauticana.personnel.model.Organization;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = MaterialInventory.TABLE_NAME)
public class MaterialInventory implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "materialInventory";
	public  static final String   TABLE_NAME  = "MATERIAL_INVENTORY";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "organizationId", column = @Column(name = "ORGANIZATION_ID", nullable = false)),
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)) })
	private MaterialInventoryId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false, insertable = false, updatable = false)
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false, insertable = false, updatable = false)
	private Organization organization;

	@Column(name = "QUANTITY", nullable = false, precision = 10)
	private BigDecimal quantity;

	@Column(name = "UNIT", nullable = false, length = 3)
	private String unit;

	@Column(name = "RESERVED", precision = 10)
	private BigDecimal reserved;

	@Column(name = "RESERVE_UNIT", length = 3)
	private String reserveUnit;

	@Column(name = "COST", precision = 10)
	private BigDecimal cost;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialInventory")
	private Set<LotInventory> lotInventories = new HashSet<LotInventory>(0);
}
