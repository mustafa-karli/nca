package com.nauticana.business.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.business.model.VendorBusiness;
import com.nauticana.business.model.VendorBusinessId;

@Service
public class VendorBusinessService extends AbstractService<VendorBusiness, VendorBusinessId> {

	@Autowired
	VendorService parentService;

	@Override
	@Transactional
	public VendorBusiness newEntityWithParentId(String parentKey) {
		VendorBusiness entity = new VendorBusiness();
		entity.setId(new VendorBusinessId());
		int key = Integer.parseInt(parentKey);
		entity.getId().setBusinessPartnerId(key);
		entity.setVendor(parentService.findById(parentService.strToId(parentKey)));
		return entity;
	}

	@Override
	public VendorBusinessId strToId(String id) {
		return new VendorBusinessId(id);
	}

	@Override
	public VendorBusiness newEntityWithId(String strId) {
		VendorBusiness entity = new VendorBusiness();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<VendorBusiness> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(VendorBusiness x : list) {
			i++;
			items[i][0] = x.getId().getNodeId()+"";
			items[i][1] = x.getBegda()+"";
		}
		return items;
	}

	@Override
	public String parentLink(VendorBusiness entity) {
		if (entity == null) return null;
		return "businessPartner/show?id=" + entity.getId().getBusinessPartnerId();
	}

	@Override
	public String idAsStr(VendorBusiness entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}

