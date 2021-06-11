package com.nauticana.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.project.model.ProjectTeam;
import com.nauticana.project.model.ProjectTeamId;
import com.nauticana.project.model.ProjectTeamTemplate;
import com.nauticana.project.model.ProjectTeamTemplateId;
import com.nauticana.project.repository.ProjectTeamRepository;

@Service
public class ProjectTeamTemplateService extends AbstractService<ProjectTeamTemplate,ProjectTeamTemplateId> {

	@Autowired
	ProjectTeamRepository parentRep;

	@Override
	public ProjectTeamTemplate newEntityWithParentId(String parentKey) {
		ProjectTeamTemplate entity = new ProjectTeamTemplate();
		if (!Utils.emptyStr(parentKey)) {
			ProjectTeamTemplateId id = new ProjectTeamTemplateId();
			ProjectTeamId parentId = new ProjectTeamId(parentKey);
			id.setProjectId(parentId.getProjectId());
			id.setTeamId(parentId.getTeamId());
			entity.setProjectTeam(parentRep.findById(parentId).get());
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public ProjectTeamTemplateId strToId(String id) {
		return new ProjectTeamTemplateId(id);
	}

	@Override
	public ProjectTeamTemplate newEntityWithId(String strId) {
		ProjectTeamTemplate entity = new ProjectTeamTemplate();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProjectTeamTemplate entity) {
		if (entity == null) return null;
		return ProjectTeam.ROOTMAPPING + "/show?id=" + entity.getProjectTeam().getId().toString();
	}

	@Override
	public String idAsStr(ProjectTeamTemplate entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}
