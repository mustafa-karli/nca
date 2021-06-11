package com.nauticana.personnel.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.FieldType;
import com.nauticana.personnel.model.Employee;
import com.nauticana.personnel.model.Position;
import com.nauticana.personnel.model.PositionAssignment;
import com.nauticana.personnel.model.PositionAssignmentId;
import com.nauticana.personnel.model.PositionId;

@Service
public class PositionAssignmentService extends AbstractService<PositionAssignment, PositionAssignmentId> {

	@Autowired
	PositionService parentService;

	@Autowired
	EmployeeService employeeService;

	@Override
	@Transactional
	public PositionAssignment newEntityWithParentId(String parentKey) {
		PositionAssignment entity = new PositionAssignment();
		entity.setId(new PositionAssignmentId());
		PositionId parentId = parentService.strToId(parentKey);
		entity.getId().setOrganizationId(parentId.getOrganizationId());
		entity.getId().setPosition(parentId.getPosition());
		entity.getId().setOwnerId(parentId.getOwnerId());
		entity.getId().setEmployment(new Date());
		entity.setPosition(parentService.findById(parentId));
		return entity;
	}

	@Override
	public boolean customerSpecific() {
		return true;
	};
	
	@Override
	public void setClient(PositionAssignment entity, int client) {
		entity.getId().setOwnerId(client);
	};

	@Override
	public int getClient(PositionAssignment entity) {
		return entity.getId().getOwnerId();
	};

	@Override
	public PositionAssignmentId strToId(String id) {
		return new PositionAssignmentId(id);
	}

	@Override
	public PositionAssignment newEntityWithId(String strId) {
		PositionAssignment entity = new PositionAssignment();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(PositionAssignment entity) {
		if (entity == null) return null;
		if (entity.getPosition() == null) return null;
		return Position.ROOTMAPPING + "/show?id=" + entity.getPosition().getId().toString();
	}

	@Override
	public PositionAssignment create(PositionAssignment entity) throws Exception {
		try {
			ArrayList<String>  fields  = new ArrayList<String>();
			ArrayList<String>  filters = new ArrayList<String>();
			ArrayList<Integer> types  = new ArrayList<Integer>();
			
			fields.add("OWNER_ID");
			fields.add("PERSON_ID");
			filters.add(entity.getId().getOwnerId()+"");
			filters.add(entity.getId().getPersonId()+"");
			types.add(FieldType.T_INT);
			types.add(FieldType.T_INT);
			
			List<Employee> records = employeeService.search(fields, filters, types);
			
			for (Employee emp : records) {
				entity.getId().setEmployment(emp.getId().getEmployment());
			}
			return r.save(entity);
		} catch(DataAccessException e) {
			throw new Exception(e.getRootCause().getMessage());
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String idAsStr(PositionAssignment entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}

