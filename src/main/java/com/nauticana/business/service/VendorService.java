package com.nauticana.business.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.business.model.Vendor;

@Service
public class VendorService extends AbstractService<Vendor, Integer> {

	@Autowired
	BusinessPartnerService parentService;

	@Override
	@Transactional
	public Vendor newEntityWithParentId(String parentKey) {
		Vendor entity = new Vendor();
		if (!Utils.emptyStr(parentKey)) {
			int parentId = parentService.strToId(parentKey);
			entity.setId(parentId);
			entity.setBusinessPartner(parentService.findById(parentService.strToId(parentKey)));
		}
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public Vendor newEntityWithId(String strId) {
		Vendor entity = new Vendor();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<Vendor> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Vendor x : list) {
			i++;
			items[i][0] = x.getId() + "";
			items[i][1] = x.getBusinessPartner().getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(Vendor entity) {
		return null;
	}

	@Override
	public String idAsStr(Vendor entity) {
		if (entity == null) return null;
		return entity.getId() + "";
	}
}

