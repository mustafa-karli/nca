package com.nauticana.personnel.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.personnel.model.PersonQualification;
import com.nauticana.personnel.model.PersonQualificationId;

@Service
public class PersonQualificationService extends AbstractService<PersonQualification, PersonQualificationId> {

	@Autowired
	PersonService parentService;

	@Override
	@Transactional
	public PersonQualification newEntityWithParentId(String parentKey) {
		PersonQualification entity = new PersonQualification();
		entity.setId(new PersonQualificationId());
		int parentId = parentService.strToId(parentKey);
		entity.getId().setPersonId(parentId);
		entity.setPerson(parentService.findById(parentId));
		return entity;
	}

	@Override
	public PersonQualificationId strToId(String id) {
		return new PersonQualificationId(id);
	}

	@Override
	public PersonQualification newEntityWithId(String strId) {
		PersonQualification entity = new PersonQualification();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(PersonQualification entity) {
		if (entity == null) return null;
		return "person/show?id=" + entity.getId().getPersonId();
	}

	@Override
	public String idAsStr(PersonQualification entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

