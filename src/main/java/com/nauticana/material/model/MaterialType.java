package com.nauticana.material.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nauticana.maintenance.model.FinalEquipmentPart;
import com.nauticana.maintenance.model.MaintenanceStep;
import com.nauticana.maintenance.model.ServiceTypeStep;
import com.nauticana.purchase.model.ProposalToRfpItem;
import com.nauticana.purchase.model.RequestForProposalItem;
import com.nauticana.request.model.ProductTypeByDefine;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "MATERIAL_TYPE")
public class MaterialType implements java.io.Serializable {

	private static final long				serialVersionUID			= 1L;

	@Id
	@Column(name = "MATERIAL_TYPE_ID", unique = true, nullable = false, length = 30)
	private String							id;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String							caption;

	@Column(name = "UNIT", nullable = false, length = 3)
	private String							unit;

	@Column(name = "MASTER", nullable = true)
	private Integer							master;

	@Column(name = "LOGO", length = 80)
	private String							logo;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialType")
	private Set<MaterialTypeAttribute>		materialTypeAttributes		= new HashSet<MaterialTypeAttribute>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialType")
	private Set<ProductTypeByDefine>		productTypeByDefines		= new HashSet<ProductTypeByDefine>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialType")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<FinalEquipmentPart>			finalEquipmentParts			= new HashSet<FinalEquipmentPart>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialType")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<RequestForProposalItem>		requestForProposalItems		= new HashSet<RequestForProposalItem>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialType")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<MaintenanceStep>			maintenanceSteps			= new HashSet<MaintenanceStep>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialType")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<ServiceTypeStep>			serviceTypeSteps			= new HashSet<ServiceTypeStep>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialType")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<ProposalToRfpItem>			proposalToRfpItems			= new HashSet<ProposalToRfpItem>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialType")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Material>					materials					= new HashSet<Material>(0);
}
