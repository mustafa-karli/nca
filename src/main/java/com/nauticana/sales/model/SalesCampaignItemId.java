package com.nauticana.sales.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SalesCampaignItemId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "SALES_CAMPAIGN_ID", nullable = false)
	private int salesCampaignId;

	@Column(name = "MATERIAL_ID", nullable = false)
	private int materialId;

	public SalesCampaignItemId(String keys) {
		String[] s = keys.split(",");
		this.salesCampaignId = Integer.parseInt(s[0]);
		this.materialId = Integer.parseInt(s[1]);
	}

	@Override
	public String toString() {
		return this.salesCampaignId + "," + this.materialId;
	}
}
