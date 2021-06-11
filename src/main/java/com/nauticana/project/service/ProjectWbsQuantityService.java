package com.nauticana.project.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.Utils;
import com.nauticana.project.model.ProjectTeamId;
import com.nauticana.project.model.ProjectWbs;
import com.nauticana.project.model.ProjectWbsId;
import com.nauticana.project.model.ProjectWbsQuantity;
import com.nauticana.project.model.ProjectWbsQuantityId;
import com.nauticana.project.repository.ProjectTeamRepository;
import com.nauticana.project.repository.ProjectWbsRepository;

@Service
public class ProjectWbsQuantityService extends AbstractService<ProjectWbsQuantity,ProjectWbsQuantityId> {

	@Autowired
	ProjectWbsRepository pwRep;

	@Autowired
	ProjectTeamRepository ptRep;

	@Override
	public ProjectWbsQuantity newEntityWithParentId(String parentKey) {
		String[] s = parentKey.split(",");
		int projectId = Integer.parseInt(s[0]);
		int categoryId = Integer.parseInt(s[1]);
		int teamId = Integer.parseInt(s[2]);
		ProjectWbsQuantity entity = new ProjectWbsQuantity();
		if (!Utils.emptyStr(parentKey)) {
			ProjectWbsId pwId = new ProjectWbsId(projectId,categoryId);
			ProjectTeamId ptId = new ProjectTeamId(projectId,teamId);
			ProjectWbsQuantityId id = new ProjectWbsQuantityId();
			id.setProjectId(projectId);
			id.setCategoryId(categoryId);
			id.setTeamId(teamId);
			entity.setId(id);
			entity.setProjectWbs(pwRep.findById(pwId).get());
			entity.setProjectTeam(ptRep.findById(ptId).get());
		}
		entity.setStatus("INITIAL");
		return entity;
	}

	@Override
	public ProjectWbsQuantityId strToId(String id) {
		String[] s = id.split(",");
		try {
			return new ProjectWbsQuantityId(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]), Labels.dmyDF.parse(s[3]));
		} catch (NumberFormatException | ParseException e) {
			return null;
		}
	}

	@Override
	public ProjectWbsQuantity newEntityWithId(String strId) {
		ProjectWbsQuantity entity = new ProjectWbsQuantity();
		entity.setId(strToId(strId));
		return entity;
	}

	public void approve(int projectId, int categoryId, int teamId, Date begda, Date endda) throws Exception {
		ProjectWbsId pwId = new ProjectWbsId(projectId, categoryId);
		ProjectWbs pw = pwRep.findById(pwId).get();
		for (ProjectWbsQuantity pwq : pw.getProjectWbsQuantities()) {
			if (pwq.getId().getTeamId() == teamId &&
				pwq.getStatus().equals(Labels.INITIAL) &&
				(begda == null || 
				 (endda == null && pwq.getId().getBegda().getTime() == begda.getTime()) ||
				 (pwq.getId().getBegda().getTime() >= begda.getTime() && pwq.getEndda().getTime() <= endda.getTime())
				))	{
				pwq.setStatus(Labels.APPROVED);
				save(pwq);
			}
		}
	}

	public void withdrawApprove(int projectId, int categoryId, int teamId, Date begda, Date endda) throws Exception {
		ProjectWbsId pwId = new ProjectWbsId(projectId, categoryId);
		ProjectWbs pw = pwRep.findById(pwId).get();
		for (ProjectWbsQuantity pwq : pw.getProjectWbsQuantities()) {
			if (pwq.getId().getTeamId() == teamId &&
					pwq.getStatus().equals(Labels.APPROVED) &&
					(begda == null || 
					 (endda == null && pwq.getId().getBegda().getTime() == begda.getTime()) ||
					 (pwq.getId().getBegda().getTime() >= begda.getTime() && pwq.getEndda().getTime() <= endda.getTime())
					))	{
					pwq.setStatus(Labels.INITIAL);
					save(pwq);
				}
		}
	}

	@Override
	public String parentLink(ProjectWbsQuantity entity) {
		if (entity == null) return null;
		return ProjectWbs.ROOTMAPPING + "/show?id=" + entity.getProjectWbs().getId().toString();
	}

	@Override
	public String idAsStr(ProjectWbsQuantity entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}
