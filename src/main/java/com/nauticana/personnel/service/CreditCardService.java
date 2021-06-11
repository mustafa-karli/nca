package com.nauticana.personnel.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.personnel.model.CreditCard;
import com.nauticana.personnel.model.Person;

@Service
public class CreditCardService extends AbstractService<CreditCard, String> {

	@Autowired
	PersonService parentService;

	@Override
	@Transactional
	public CreditCard newEntityWithParentId(String parentKey) {
		CreditCard entity = new CreditCard();
		int parentId = parentService.strToId(parentKey);
		Person parent = parentService.findById(parentId);
		entity.setPerson(parent);
		return entity;
	}

	@Override
	public String strToId(String id) {
		return id;
	}

	@Override
	public CreditCard newEntityWithId(String strId) {
		CreditCard entity = new CreditCard();
		entity.setId(strId);
		return entity;
	}

	@Override
	public String parentLink(CreditCard entity) {
		if (entity == null) return null;
		if (entity.getPerson() == null) return null;
		return "person/show?id=" + entity.getPerson().getId();
	}

	@Override
	public String idAsStr(CreditCard entity) {
		if (entity != null)
			return entity.getId();
		else
			return null;
	}
	
}

