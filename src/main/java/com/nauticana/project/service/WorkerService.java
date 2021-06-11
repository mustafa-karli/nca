package com.nauticana.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.business.model.Subcontractor;
import com.nauticana.business.repository.SubcontractorRepository;
import com.nauticana.personnel.model.Person;
import com.nauticana.project.model.Worker;
import com.nauticana.project.repository.WorkerRepository;

@Service
public class WorkerService extends AbstractService<Worker, Integer> {

	@Autowired
	SubcontractorRepository parentRep;
	
	@Override
	public Worker newEntityWithParentId(String parentKey) {
		Worker entity = new Worker();
		if (!Utils.emptyStr(parentKey)) {
			entity.setSubcontractor(parentRep.findById(strToId(parentKey)).get());
		}
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}
	
	public Worker findByPersonId(int personId) {
		return ((WorkerRepository) r).findByPersonId(personId);
	}
	
	public List<Worker> findBySubcontractor(Subcontractor subcontractor) {
		return ((WorkerRepository) r).findBySubcontractor(subcontractor);
	}

	@Override
	public Worker newEntityWithId(String strId) {
		Worker entity = new Worker();
		entity.setId(strToId(strId));
		return entity;
	}

	public Worker getPerson(Person lp) {
		if (lp == null) return null;
		Worker entity = findByPersonId(lp.getId());
		if (entity == null) {
			entity = new Worker();
			entity.setCaption(lp.getFirstName() + " " + lp.getLastName());
			entity.setPersonId(lp.getId());
			entity.setCitizenShip(lp.getNationality());
//			if (!Utils.emptyStr(lp.getSubcontractor())) {
//				List<Subcontractor> subcontractor = parentRep.findByExtSubcontractor(lp.getSubcontractor());
//				if (!subcontractor.isEmpty())
//					entity.setSubcontractor(subcontractor.get(0));
//			}
			entity = ((WorkerRepository) r).save(entity);
		}
		return entity;
	}

	@Override
	public String parentLink(Worker entity) {
		if (entity == null) return null;
		if (entity.getSubcontractor() == null) return null;
		return Subcontractor.ROOTMAPPING + "/show?id=" + entity.getSubcontractor().getId();
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<Worker> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Worker x : list) {
			i++;
			items[i][0] = x.getId()+"";
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String idAsStr(Worker entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}
}
