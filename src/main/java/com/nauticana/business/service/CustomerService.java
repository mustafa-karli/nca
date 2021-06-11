package com.nauticana.business.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.business.model.Customer;

@Service
public class CustomerService extends AbstractService<Customer, Integer> {

	@Autowired
	BusinessPartnerService parentService;

	@Override
	@Transactional
	public Customer newEntityWithParentId(String parentKey) {
		Customer entity = new Customer();
		if (!Utils.emptyStr(parentKey)) {
			int id = Integer.parseInt(parentKey);
			entity.setId(id);
			entity.setBusinessPartner(parentService.findById(parentService.strToId(parentKey)));
		}
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public Customer newEntityWithId(String strId) {
		Customer entity = new Customer();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<Customer> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Customer x : list) {
			i++;
			items[i][0] = x.getId()+"";
			items[i][1] = x.getBusinessPartner().getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(Customer entity) {
		return null;
	}

	@Override
	public String idAsStr(Customer entity) {
		if (entity == null) return null;
		return entity.getId() + "";
	}

}

