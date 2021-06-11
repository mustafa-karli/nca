package com.nauticana.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.project.model.ProjectTeam;
import com.nauticana.project.model.ProjectTeamId;
import com.nauticana.project.model.ProjectTeamPerson;
import com.nauticana.project.model.ProjectTeamPersonId;
import com.nauticana.project.repository.ProjectTeamRepository;

@Service
public class ProjectTeamPersonService extends AbstractService<ProjectTeamPerson,ProjectTeamPersonId> {

	@Autowired
	ProjectTeamRepository parentRep;

	@Override
	public ProjectTeamPerson newEntityWithParentId(String parentKey) {
		ProjectTeamPerson entity = new ProjectTeamPerson();
		if (!Utils.emptyStr(parentKey)) {
			ProjectTeamPersonId id = new ProjectTeamPersonId();
			ProjectTeamId parentId = new ProjectTeamId(parentKey);
			id.setProjectId(parentId.getProjectId());
			id.setTeamId(parentId.getTeamId());
			entity.setProjectTeam(parentRep.findById(parentId).get());
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public ProjectTeamPersonId strToId(String id) {
		return new ProjectTeamPersonId(id);
	}

	@Override
	public ProjectTeamPerson newEntityWithId(String strId) {
		ProjectTeamPerson entity = new ProjectTeamPerson();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProjectTeamPerson entity) {
		if (entity == null) return null;
		return ProjectTeam.ROOTMAPPING + "/show?id=" + entity.getProjectTeam().getId().toString();
	}

	@Override
	public String idAsStr(ProjectTeamPerson entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}
