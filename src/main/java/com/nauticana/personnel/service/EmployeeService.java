package com.nauticana.personnel.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.business.service.BusinessOwnerService;
import com.nauticana.personnel.model.Employee;
import com.nauticana.personnel.model.EmployeeId;

@Service
public class EmployeeService extends AbstractService<Employee, EmployeeId> {

	@Autowired
	BusinessOwnerService parentService;

	@Override
	@Transactional
	public Employee newEntityWithParentId(String parentKey) {
		Employee entity = new Employee();
//		entity.setOwnerId(Integer.parseInt(parentKey));
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(Employee entity, int client) {
		entity.setOwnerId(client);
	};

	@Override
	public int getClient(Employee entity) {
		return entity.getOwnerId();
	};

	@Override
	public EmployeeId strToId(String id) {
		return new EmployeeId(id);
	}

	@Override
	public Employee newEntityWithId(String strId) {
		Employee entity = new Employee();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<Employee> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Employee x : list) {
			i++;
			items[i][0] = x.getId().getPersonId()+"";
			items[i][1] = x.getPerson().getFirstName() + " " + x.getPerson().getLastName();
		}
		return items;
	}

	@Override
	public String parentLink(Employee entity) {
		return null;
	}

	@Override
	public String idAsStr(Employee entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}
}

