package com.nauticana.purchase.model;

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

import com.nauticana.material.model.Manufacturer;
import com.nauticana.material.model.Material;
import com.nauticana.material.model.MaterialType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "REQUEST_FOR_PROPOSAL_ITEM")
public class RequestForProposalItem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "rfpId", column = @Column(name = "RFP_ID", nullable = false)),
		@AttributeOverride(name = "line", column = @Column(name = "LINE", nullable = false)) })
	private RequestForProposalItemId	id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID")
	private Material					material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANUFACTURER_ID")
	private Manufacturer				manufacturer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_TYPE_ID")
	private MaterialType				materialType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RFP_ID", nullable = false, insertable = false, updatable = false)
	private RequestForProposal			requestForProposal;

	@Column(name = "SPECIFICATION", nullable = false, length = 80)
	private String						specification;

	@Column(name = "QUANTITY", nullable = false, precision = 12)
	private BigDecimal					quantity;

	@Column(name = "UNIT", nullable = false, length = 3)
	private String						unit;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "requestForProposalItem")
	private Set<ProposalToRfpItem>		proposalToRfpItems	= new HashSet<ProposalToRfpItem>(0);
}
