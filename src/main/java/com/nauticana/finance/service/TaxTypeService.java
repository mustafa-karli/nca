package com.nauticana.finance.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.finance.model.TaxType;

@Service
public class TaxTypeService extends AbstractService<TaxType, String> {

	@Override
	@Transactional
	public TaxType newEntityWithParentId(String parentKey) {
		TaxType entity = new TaxType();
		return entity;
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public TaxType newEntityWithId(String strId) {
		TaxType entity = new TaxType();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<TaxType> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(TaxType x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(TaxType entity) {
		return null;
	}

	@Override
	public String idAsStr(TaxType entity) {
		if (entity == null) return null;
		return entity.getId();
	}
}

