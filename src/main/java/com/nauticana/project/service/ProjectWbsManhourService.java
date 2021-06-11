package com.nauticana.project.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.Utils;
import com.nauticana.project.model.ProjectTeamPersonId;
import com.nauticana.project.model.ProjectWbs;
import com.nauticana.project.model.ProjectWbsId;
import com.nauticana.project.model.ProjectWbsManhour;
import com.nauticana.project.model.ProjectWbsManhourId;
import com.nauticana.project.repository.ProjectTeamPersonRepository;
import com.nauticana.project.repository.ProjectWbsRepository;

@Service
public class ProjectWbsManhourService extends AbstractService<ProjectWbsManhour,ProjectWbsManhourId> {

	@Autowired
	ProjectWbsRepository pwRep;

	@Autowired
	ProjectTeamPersonRepository parentRep;

	@Override
	public ProjectWbsManhour newEntityWithParentId(String parentKey) {
		ProjectWbsManhour entity = new ProjectWbsManhour();
		if (!Utils.emptyStr(parentKey)) {
			ProjectTeamPersonId parentId = new ProjectTeamPersonId(parentKey);
			ProjectWbsManhourId id = new ProjectWbsManhourId();
			id.setProjectId(parentId.getProjectId());
			id.setTeamId(parentId.getTeamId());
			id.setWorkerId(parentId.getWorkerId());
			entity.setProjectTeamPerson(parentRep.findById(parentId).get());
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public ProjectWbsManhourId strToId(String id) {
		String[] s = id.split(",");
		Date date;
		try {
			date = Labels.dmyDF.parse(s[4]);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return new ProjectWbsManhourId(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3]),date);
	}

	@Override
	public ProjectWbsManhour newEntityWithId(String strId) {
		ProjectWbsManhour entity = new ProjectWbsManhour();
		entity.setId(strToId(strId));
		return entity;
	}

	public void approve(int projectId, int categoryId, int teamId, Date begda, Date endda) throws Exception {
		ProjectWbsId pwId = new ProjectWbsId(projectId, categoryId);
		ProjectWbs pw = pwRep.findById(pwId).get();
		for (ProjectWbsManhour pwm : pw.getProjectWbsManhours())
			if (pwm.getId().getTeamId() == teamId &&
				pwm.getStatus().equals(Labels.INITIAL) &&
				(begda == null || 
				 (endda == null && pwm.getId().getActivityDate().getTime() == begda.getTime()) ||
				 (pwm.getId().getActivityDate().getTime() >= begda.getTime() && pwm.getId().getActivityDate().getTime() <= endda.getTime())
				))	{
					pwm.setStatus(Labels.APPROVED);
					save(pwm);
				}	
	}
	
	public void withdrawApprove(int projectId, int categoryId, int teamId, Date begda, Date endda) throws Exception {
		ProjectWbsId pwId = new ProjectWbsId(projectId, categoryId);
		ProjectWbs pw = pwRep.findById(pwId).get();
		for (ProjectWbsManhour pwm : pw.getProjectWbsManhours())
			if (pwm.getId().getTeamId() == teamId &&
			pwm.getStatus().equals(Labels.APPROVED) &&
			(begda == null || 
			 (endda == null && pwm.getId().getActivityDate().getTime() == begda.getTime()) ||
			 (pwm.getId().getActivityDate().getTime() >= begda.getTime() && pwm.getId().getActivityDate().getTime() <= endda.getTime())
			))	{
				pwm.setStatus(Labels.INITIAL);
				save(pwm);
			}
	}

	@Override
	public String parentLink(ProjectWbsManhour entity) {
		if (entity == null) return null;
		return ProjectWbs.ROOTMAPPING + "/show?id=" + entity.getProjectWbs().getId().toString();
	}

	@Override
	public String idAsStr(ProjectWbsManhour entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}
