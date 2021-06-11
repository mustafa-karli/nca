package com.nauticana.material.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nauticana.maintenance.model.Equipment;
import com.nauticana.maintenance.model.FinalEquipmentPart;
import com.nauticana.motifarge.model.ProductPriceCommitment;
import com.nauticana.production.model.BomOperation;
import com.nauticana.production.model.BomOperationStep;
import com.nauticana.production.model.ProductionOrderItem;
import com.nauticana.purchase.model.ProposalToRfpItem;
import com.nauticana.purchase.model.PurchaseDeliveryLine;
import com.nauticana.purchase.model.PurchaseOrderItem;
import com.nauticana.purchase.model.RequestForProposalItem;
import com.nauticana.request.model.MaterialRequestItem;
import com.nauticana.request.model.ProductByDefine;
import com.nauticana.sales.model.SalesCampaignItem;
import com.nauticana.sales.model.SalesOrderItem;
import com.nauticana.shipment.model.ShipmentLine;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "MATERIAL")
public class Material implements java.io.Serializable {

	private static final long				serialVersionUID			= 1L;
	public static final String				SEQUENCE_NAME				= "MATERIAL_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "MATERIAL_ID", unique = true, nullable = false)
	private int								id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANUFACTURER_ID", nullable = true)
	private Manufacturer					manufacturer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_TYPE_ID", nullable = false)
	private MaterialType					materialType;

	@Column(name = "OWNER_ID", nullable = false)
	private int								ownerId;

	@Column(name = "PART_NUMBER", length = 30)
	private String							partNumber;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String							caption;

	@Column(name = "DEFAULT_UNIT", nullable = false, length = 3)
	private String							defaultUnit;

	@Column(name = "PURCHASE_UNIT", length = 8)
	private String							purchaseUnit;

	@Column(name = "ISSUE_UNIT", length = 8)
	private String							issueUnit;

	@Column(name = "SHELF_LIFE")
	private Short							shelfLife;

	@Column(name = "COST_METHOD", length = 8)
	private String							costMethod;

	@Column(name = "COST", precision = 10)
	private BigDecimal						cost;

	@Column(name = "STATUS", length = 1)
	private String							status;

	@Column(name = "ISSUE_TYPE", length = 1)
	private String							issueType;

	@Column(name = "EQUIPMENT", length = 1)
	private String							equipment;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<MaterialSalePrice>			materialSalePrices			= new HashSet<MaterialSalePrice>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<MaterialGroupAssignment>	materialGroupAssignments	= new HashSet<MaterialGroupAssignment>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<ProductByDefine>			productByDefines			= new HashSet<ProductByDefine>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<MaterialPackUnit>			materialPackUnits			= new HashSet<MaterialPackUnit>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<MaterialRequestItem>		materialRequestItems		= new HashSet<MaterialRequestItem>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<MaterialInventory>			materialInventories			= new HashSet<MaterialInventory>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<SalesCampaignItem>			salesCampaignItems			= new HashSet<SalesCampaignItem>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<MaterialSaleTax>			materialSaleTaxes			= new HashSet<MaterialSaleTax>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<ProposalToRfpItem>			proposalToRfpItems			= new HashSet<ProposalToRfpItem>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<MaterialReservation>		materialReservations		= new HashSet<MaterialReservation>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<ProductPriceCommitment>		productPriceCommitments		= new HashSet<ProductPriceCommitment>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<FinalEquipmentPart>			finalEquipmentParts			= new HashSet<FinalEquipmentPart>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<BomOperation>				bomOperations				= new HashSet<BomOperation>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<ShipmentLine>				shipmentLines				= new HashSet<ShipmentLine>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<MaterialAttribute>			materialAttributes			= new HashSet<MaterialAttribute>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<MaterialInventoryHistory>	materialInventoryHistories	= new HashSet<MaterialInventoryHistory>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<ProductionOrderItem>		productionOrderItems		= new HashSet<ProductionOrderItem>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<RequestForProposalItem>		requestForProposalItems		= new HashSet<RequestForProposalItem>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<Equipment>					equipments					= new HashSet<Equipment>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<BomOperationStep>			bomOperationSteps			= new HashSet<BomOperationStep>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<MaterialMovement>			materialMovements			= new HashSet<MaterialMovement>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<PurchaseDeliveryLine>		purchaseDeliveryLines		= new HashSet<PurchaseDeliveryLine>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<SalesOrderItem>				salesOrderItems				= new HashSet<SalesOrderItem>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	private Set<PurchaseOrderItem>			purchaseOrderItems			= new HashSet<PurchaseOrderItem>(0);
}
