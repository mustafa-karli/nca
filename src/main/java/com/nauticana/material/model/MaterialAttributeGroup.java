package com.nauticana.material.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nauticana.maintenance.model.EquipmentAttribute;
import com.nauticana.purchase.model.PurchaseOrderItemAttr;
import com.nauticana.request.model.MaterialRequestItemAttr;
import com.nauticana.sales.model.SalesOrderItemAttr;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "MATERIAL_ATTRIBUTE_GROUP")
public class MaterialAttributeGroup implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MAG_ID", unique = true, nullable = false, length = 8)
	private String							id;

	@Column(name = "OWNER_ID", nullable = false)
	private int								ownerId;

	@Column(name = "CAPTION", nullable = false, length = 30)
	private String							caption;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialAttributeGroup")
	private Set<MaterialRequestItemAttr>	materialRequestItemAttrs	= new HashSet<MaterialRequestItemAttr>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialAttributeGroup")
	private Set<SalesOrderItemAttr>			salesOrderItemAttrs			= new HashSet<SalesOrderItemAttr>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialAttributeGroup")
	private Set<EquipmentAttribute>			equipmentAttributes			= new HashSet<EquipmentAttribute>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialAttributeGroup")
	private Set<MaterialAttributeOption>	materialAttributeOptions	= new HashSet<MaterialAttributeOption>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialAttributeGroup")
	private Set<MaterialTypeAttribute>	    materialTypeAttributes	    = new HashSet<MaterialTypeAttribute>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialAttributeGroup")
	private Set<MaterialAttribute>			materialAttributes			= new HashSet<MaterialAttribute>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialAttributeGroup")
	private Set<PurchaseOrderItemAttr>		purchaseOrderItemAttrs		= new HashSet<PurchaseOrderItemAttr>(0);
}
