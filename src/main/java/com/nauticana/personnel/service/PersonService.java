package com.nauticana.personnel.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.personnel.model.Person;

@Service
public class PersonService extends AbstractService<Person, Integer> {

	@Override
	@Transactional
	public Person newEntityWithParentId(String parentKey) {
		Person entity = new Person();
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public Person newEntityWithId(String strId) {
		Person entity = new Person();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<Person> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Person x : list) {
			i++;
			items[i][0] = x.getId() + "";
			items[i][1] = x.getFirstName() + " " + x.getLastName();
		}
		return items;
	}

	@Override
	public String parentLink(Person entity) {
		return null;
	}

	@Override
	public String idAsStr(Person entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}
}

