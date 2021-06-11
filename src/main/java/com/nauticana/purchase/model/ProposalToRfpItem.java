package com.nauticana.purchase.model;

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

import com.nauticana.material.model.Material;
import com.nauticana.material.model.MaterialType;

import lombok.Data;

@Data
@Entity
@Table(name = "PROPOSAL_TO_RFP_ITEM")
public class ProposalToRfpItem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "proposalId", column = @Column(name = "PROPOSAL_ID", nullable = false)),
		@AttributeOverride(name = "rfpId", column = @Column(name = "RFP_ID", nullable = false)),
		@AttributeOverride(name = "line", column = @Column(name = "LINE", nullable = false)) })
	private ProposalToRfpItemId		id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false)
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_TYPE_ID", nullable = false)
	private MaterialType materialType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROPOSAL_ID", nullable = false, insertable = false, updatable = false)
	private ProposalToRfp proposalToRfp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "RFP_ID", referencedColumnName = "RFP_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "LINE", referencedColumnName = "LINE", nullable = false, insertable = false, updatable = false) })
	private RequestForProposalItem requestForProposalItem;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "QUANTITY", nullable = false, precision = 12)
	private BigDecimal quantity;

	@Column(name = "UNIT", nullable = false, length = 2)
	private String unit;

	@Column(name = "UNIT_PRICE", nullable = false, precision = 12)
	private BigDecimal unitPrice;

	@Column(name = "DISCOUNT_PCT", nullable = false)
	private short discountPct;

	@Column(name = "CURRENCY", nullable = false, length = 3)
	private String currency;

	@Column(name = "TAX_PCT", nullable = false, precision = 4)
	private float taxPct;

	public boolean modified(int proposalId,	short line, int materialId, String materialTypeId, String mfrc, String partNumber, int ownerId, double quantity, String unit, double unitPrice, short discountPct, String currency, float taxPct) {
		if (id.getProposalId() != proposalId || id.getLine() != line || this.ownerId != ownerId || this.quantity.doubleValue() != quantity || this.unitPrice.doubleValue() != unitPrice || this.discountPct != discountPct || this.taxPct != taxPct) return true;
		if (this.material == null || this.materialType == null || this.unit == null || this.currency == null) return true;
		if (this.material.getId() != materialId || !this.material.getManufacturer().getId().equals(mfrc) || !this.material.getPartNumber().equals(partNumber)) return true;
		if (!this.unit.equals(unit) || !this.currency.equals(currency)) return true;
		return false;
	}
}
