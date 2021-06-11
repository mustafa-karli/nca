package com.nauticana.sales.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.sales.model.SalesCampaign;
import com.nauticana.sales.model.SalesCampaignItem;
import com.nauticana.sales.model.SalesCampaignItemId;

@Service
public class SalesCampaignItemService extends AbstractService<SalesCampaignItem, SalesCampaignItemId> {

	@Autowired
	SalesCampaignService parentService;

	@Override
	@Transactional
	public SalesCampaignItem newEntityWithParentId(String parentKey) {
		SalesCampaignItem entity = new SalesCampaignItem();
		entity.setId(new SalesCampaignItemId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setSalesCampaignId(parentId);
		entity.setSalesCampaign(parentService.findById(parentId));
		return entity;
	}

	@Override
	public SalesCampaignItemId strToId(String id) {
		return new SalesCampaignItemId(id);
	}

	@Override
	public SalesCampaignItem newEntityWithId(String strId) {
		SalesCampaignItem entity = new SalesCampaignItem();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(SalesCampaignItem entity) {
		if (entity == null) return null;
		return SalesCampaign.ROOTMAPPING + "/show?id=" + entity.getId().getSalesCampaignId();
	}

	@Override
	public String idAsStr(SalesCampaignItem entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

