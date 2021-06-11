package com.nauticana.personnel.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nauticana.business.model.PartnerAddress;
import com.nauticana.maintenance.model.EquipmentLocation;
import com.nauticana.maintenance.model.Maintenance;
import com.nauticana.material.model.MaterialInventory;
import com.nauticana.material.model.MaterialInventoryHistory;
import com.nauticana.production.model.ProductionJob;
import com.nauticana.production.model.ProductionOrder;
import com.nauticana.production.model.ProductionResource;
import com.nauticana.purchase.model.PurchaseDelivery;
import com.nauticana.purchase.model.PurchaseOrder;
import com.nauticana.request.model.MaterialRequest;
import com.nauticana.request.model.ProductTypeByDefine;
import com.nauticana.shipment.model.Shipment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = Organization.TABLE_NAME)
public class Organization implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "organization";
	public  static final String   TABLE_NAME  = "ORGANIZATION";
	public  static final String   SEQUENCE_NAME = "ORGANIZATION_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "ORGANIZATION_ID", unique = true, nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private Organization organization;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "OWNER_ID", referencedColumnName = "BUSINESS_PARTNER_ID", nullable = false),
		@JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID", nullable = false) })
	private PartnerAddress partnerAddress;

	@Column(name = "ORGANIZATION_TYPE", nullable = false, length = 2)
	private String organizationType;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String caption;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<ProductionJob> productionJobs = new HashSet<ProductionJob>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<PurchaseOrder> purchaseOrders = new HashSet<PurchaseOrder>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<PurchaseDelivery> purchaseDeliveries = new HashSet<PurchaseDelivery>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<EquipmentLocation> equipmentLocations = new HashSet<EquipmentLocation>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<Maintenance> maintenances = new HashSet<Maintenance>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<Position> positions = new HashSet<Position>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<MaterialInventory> materialInventories = new HashSet<MaterialInventory>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<ProductionResource> productionResources = new HashSet<ProductionResource>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<MaterialInventoryHistory> materialInventoryHistories = new HashSet<MaterialInventoryHistory>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<Organization> organizations = new HashSet<Organization>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<MaterialRequest> materialRequests = new HashSet<MaterialRequest>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<Shipment> shipments = new HashSet<Shipment>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<ProductionOrder> productionOrders = new HashSet<ProductionOrder>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<ProductTypeByDefine>		productTypeByDefines		= new HashSet<ProductTypeByDefine>(0);
}
