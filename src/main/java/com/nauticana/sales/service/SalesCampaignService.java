package com.nauticana.sales.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.sales.model.SalesCampaign;

@Service
public class SalesCampaignService extends AbstractService<SalesCampaign, Integer> {

	@Override
	@Transactional
	public SalesCampaign newEntityWithParentId(String parentKey) {
		SalesCampaign entity = new SalesCampaign();
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(SalesCampaign entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(SalesCampaign entity) {
		return entity.getOwnerId();
	};

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public SalesCampaign newEntityWithId(String strId) {
		SalesCampaign entity = new SalesCampaign();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<SalesCampaign> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(SalesCampaign x : list) {
			i++;
			items[i][0] = x.getId()+"";
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(SalesCampaign entity) {
		return null;
	}

	@Override
	public String idAsStr(SalesCampaign entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}
}

