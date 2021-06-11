package com.nauticana.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.project.model.Project;

@Service
public class ProjectService extends AbstractService<Project, Integer> {

	@Override
	@Transactional
	public Project newEntityWithParentId(String parentKey) {
		Project entity = new Project();
		entity.setStatus("INITIAL");
		entity.setDurationType("D");
		return entity;
	}

	@Override
	public Integer strToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public Project newEntityWithId(String strId) {
		Project entity = new Project();
		entity.setId(strToId(strId));
		entity.setStatus("INITIAL");
		entity.setDurationType("D");
		return entity;
	}

	@Override
	public String[][] findAllForLookup(int client) {
		List<Project> list;
		if (customerSpecific())
			list = findAll(client);
		else
			list = findAll();
		String[][] items = new String[list.size()][2];
		int i = -1;
		for(Project x : list) {
			i++;
			items[i][0] = x.getId()+"";
			items[i][1] = x.getCaption();
		}
		return items;
	}

	@Override
	public String parentLink(Project entity) {
		return null;
	}

	@Override
	public String idAsStr(Project entity) {
		if (entity != null)
			return entity.getId() + "";
		else
			return null;
	}

}
