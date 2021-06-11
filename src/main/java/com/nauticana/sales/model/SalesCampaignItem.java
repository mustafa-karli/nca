package com.nauticana.sales.model;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nauticana.material.model.Material;

import lombok.Data;

@Data
@Entity
@Table(name = SalesCampaignItem.TABLE_NAME)
public class SalesCampaignItem implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "salesCampaignItem";
	public  static final String   TABLE_NAME  = "SALES_CAMPAIGN_ITEM";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "salesCampaignId", column = @Column(name = "SALES_CAMPAIGN_ID", nullable = false)),
			@AttributeOverride(name = "materialId", column = @Column(name = "MATERIAL_ID", nullable = false)) })
	private SalesCampaignItemId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_ID", nullable = false, insertable = false, updatable = false)
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SALES_CAMPAIGN_ID", nullable = false, insertable = false, updatable = false)
	private SalesCampaign salesCampaign;

	@Column(name = "PRICE", nullable = false, precision = 12)
	private BigDecimal price;
}
