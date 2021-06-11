package com.nauticana.business.service;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.business.model.PartnerAddress;
import com.nauticana.business.model.PartnerAddressId;

@Service
public class PartnerAddressService extends AbstractService<PartnerAddress, PartnerAddressId> {

	@Autowired
	BusinessPartnerService parentService;

//	@Override
//	public boolean customerSpecific() {
//		return true;
//	};

	@Override
	@Transactional
	public PartnerAddress newEntityWithParentId(String parentKey) {
		PartnerAddress entity = new PartnerAddress();
		entity.setId(new PartnerAddressId());
		int key = Integer.parseInt(parentKey);
		entity.getId().setBusinessPartnerId(key);
		entity.setBusinessPartner(parentService.findById(parentService.strToId(parentKey)));
		return entity;
	}

	@Override
	public PartnerAddressId strToId(String id) {
		return new PartnerAddressId(id);
	}

	@Override
	public PartnerAddress newEntityWithId(String strId) {
		PartnerAddress entity = new PartnerAddress();
		entity.setId(strToId(strId));
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartnerAddress> findAll(int client) {
		if (client > 0) {
			String findAllSql = "SELECT T.* FROM " + tableName() + " T WHERE BUSINESS_PARTNER_ID=?";
			Query query = entityManager.createNativeQuery(findAllSql, PartnerAddress.class);
			query.setParameter(1, client);
			return query.getResultList();
		} else
			return r.findAll();
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<PartnerAddress> list;
		if (client > 0)
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(PartnerAddress x : list) {
			i++;
			items[i][0] = x.getId().getAddressId()+"";
			if (x.getState() == null)
				items[i][1] = x.getStreet() + " " + x.getCity() + " " + x.getCountry();
			else
				items[i][1] = x.getStreet() + " " + x.getCity() + " " + x.getState() + " " + x.getCountry();
		}
		return items;
	}

	@Override
	public String parentLink(PartnerAddress entity) {
		if (entity == null) return null;
		return "businessPartner/show?id=" + entity.getId().getBusinessPartnerId();
	}

	@Override
	public String idAsStr(PartnerAddress entity) {
		if (entity == null) return null;
		return entity.getId().toString();
	}
}

