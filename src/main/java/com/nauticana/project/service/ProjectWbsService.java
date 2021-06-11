package com.nauticana.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Utils;
import com.nauticana.project.model.Project;
import com.nauticana.project.model.ProjectWbs;
import com.nauticana.project.model.ProjectWbsId;
import com.nauticana.project.repository.ProjectRepository;

@Service
public class ProjectWbsService extends AbstractService<ProjectWbs,ProjectWbsId> {

	@Autowired
	ProjectRepository parentRep;

	@Override
	public ProjectWbs newEntityWithParentId(String parentKey) {
		ProjectWbs entity = new ProjectWbs();
		if (!Utils.emptyStr(parentKey)) {
			ProjectWbsId id = new ProjectWbsId();
			id.setProjectId(Integer.parseInt(parentKey));
			entity.setId(id);
			entity.setProject(parentRep.findById(id.getProjectId()).get());
		}
		return entity;
	}

	@Override
	public ProjectWbsId strToId(String id) {
		String[] s = id.split(",");
		return new ProjectWbsId(Integer.parseInt(s[0]),Integer.parseInt(s[1]));
	}

	@Override
	public ProjectWbs newEntityWithId(String strId) {
		ProjectWbs entity = new ProjectWbs();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProjectWbs entity) {
		if (entity == null) return null;
		return Project.ROOTMAPPING + "/show?id=" + entity.getId().getProjectId();
	}

	@Override
	public String idAsStr(ProjectWbs entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}
