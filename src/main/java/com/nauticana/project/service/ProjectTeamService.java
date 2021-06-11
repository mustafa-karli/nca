package com.nauticana.project.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.abstrct.AbstractService;
import com.nauticana.basis.utils.CategoryComparator;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.Utils;
import com.nauticana.project.model.Category;
import com.nauticana.project.model.Project;
import com.nauticana.project.model.ProjectTeam;
import com.nauticana.project.model.ProjectTeamId;
import com.nauticana.project.model.ProjectTeamPerson;
import com.nauticana.project.model.ProjectWbsManhour;
import com.nauticana.project.model.ProjectWbsManhourId;
import com.nauticana.project.repository.ProjectRepository;

@Service
public class ProjectTeamService extends AbstractService<ProjectTeam,ProjectTeamId> {

	@Autowired
	ProjectRepository parentRep;

	@Autowired
	ManhourJdbcService urepRep;

	@Override
	public ProjectTeam newEntityWithParentId(String parentKey) {
		ProjectTeam entity = new ProjectTeam();
		if (!Utils.emptyStr(parentKey)) {
			ProjectTeamId id = new ProjectTeamId();
			id.setProjectId(Integer.parseInt(parentKey));
			id.setTeamId(urepRep.nextTeamId(id.getProjectId()));
			entity.setId(id);
			entity.setProject(parentRep.findById(id.getProjectId()).get());
		}
		return entity;
	}

	@Override
	public ProjectTeamId strToId(String id) {
		return new ProjectTeamId(id);
	}

	public String[][] getWbsManhourLeadByDate(int projectId, int teamId, int workerId, Date date, ProjectWbsManhourService pwms, List<Category> c, String[] captions) {
		String[][] s = new String[c.size()+1][7];

		s[0][0] = "ID";
		s[0][1] = "CODE";
		s[0][2] = "CAPTION";
		s[0][3] = "MANHOUR";
		s[0][4] = "LOCAL_MH";
		s[0][5] = "FOREIGN_MH";
		s[0][6] = "TR_MH";
		
		ProjectWbsManhourId id = new ProjectWbsManhourId(projectId, 0, teamId, workerId, date);

		for (int i = 0; i < c.size(); i++) {
			Category ct = c.get(i);
			s[i+1][0] = ct.getId()+"";
			s[i+1][1] = ct.getTreeCode();
//			s[i+1][2] = ct.getCaption();
			s[i+1][2] = captions[i];
			id.setCategoryId(ct.getId());
			ProjectWbsManhour data = pwms.findById(id);
			if (data != null) {
				s[i+1][3] = data.getManhour()+"";
				s[i+1][4] = data.getLocalMh()+"";
				s[i+1][5] = data.getForeignMh()+"";
				s[i+1][6] = data.getTrMh()+"";
			} else {
				s[i+1][3] = "";
				s[i+1][4] = "";
				s[i+1][5] = "";
				s[i+1][6] = "";
			}

		}
		return s;
	}


	public String[][] getWbsManhourByDate(int projectId, int teamId, Date date, ProjectWbsManhourService pwms, List<Category> c, String[] captions) {
		ProjectTeam team = r.findById(new ProjectTeamId(projectId, teamId)).get();
		ArrayList<ProjectTeamPerson> p = new ArrayList<ProjectTeamPerson>(team.getProjectTeamPersonnel());

		String[][] s = new String[p.size()+4][c.size()+2];

		//s[0][0] = Labels.dmyDF.format(date);
		s[0][0] = "";
		s[0][1] = "";
		s[1][1] = team.getCaption();
		for (int i = 0; i < c.size(); i++) {
			s[0][i+2] = c.get(i).getId()+"";
			s[1][i+2] = c.get(i).getTreeCode();
//			s[2][i+2] = c.get(i).getCaption();
			s[2][i+2] = captions[i];
		}
		int[] totals = new int[c.size()+2];
		for (int i = 0; i < totals.length; i++) {
			totals[i] = 0;
		}
		
		ProjectWbsManhourId id = new ProjectWbsManhourId(projectId, 0, teamId, 0, date);
		for (int i = 0; i < p.size(); i++) {
			id.setWorkerId(p.get(i).getId().getWorkerId());
			s[i+3][0] = p.get(i).getWorker().getId()+"";
			s[i+3][1] = p.get(i).getWorker().getCaption();
			for (int j = 0; j < c.size(); j++) {
				id.setCategoryId(c.get(j).getId());
				ProjectWbsManhour data = pwms.findById(id);
				if (data != null) {
					s[i + 3][j + 2] = data.getManhour()+"";
					totals[j+2] = totals[j+2] + data.getManhour();
				} else {
					s[i + 3][j+2] = "";
				}
			}
		}
		s[p.size()+3][0] = "-1";
		s[p.size()+3][1] = Labels.TOTAL;
		for (int i = 2; i < totals.length; i++) {
			s[p.size()+3][i] = totals[i] + "";
		}
		return s;
	}
	
	
	public String[][] getWbsManhourByDateOvertime(int projectId, int teamId, Date date, ProjectWbsManhourService pwms) {
		ProjectTeam team = r.findById(new ProjectTeamId(projectId, teamId)).get();
		ArrayList<ProjectTeamPerson> p = new ArrayList<ProjectTeamPerson>(team.getProjectTeamPersonnel());
		ArrayList<Category> c = new ArrayList<Category>();
		for (Category x : team.getProjectTeamCategory()) c.add(x);
		Collections.sort(c, new CategoryComparator());

		
		String[][] s = new String[p.size()+3][c.size()*2+2];

		s[0][0] = Labels.dmyDF.format(date);
		s[1][0] = team.getCaption();
		for (int i = 0; i < c.size(); i++) {
			s[0][i*2+2] = c.get(i).getId()+"";
			s[1][i*2+2] = c.get(i).getCaption() + " " + c.get(i).getTreeCode();
			s[2][i*2+2] = "MH";
			s[2][i*2+3] = "OT";
		}
		
//		int[] totals = new int[c.size()*2+2];
//		for (int i = 0; i < totals.length; i++) {
//			totals[i] = 0;
//		}
		ProjectWbsManhourId id = new ProjectWbsManhourId(projectId, 0, teamId, 0, date);
		for (int i = 0; i < p.size(); i++) {
			id.setWorkerId(p.get(i).getId().getWorkerId());
			s[i+3][0] = p.get(i).getWorker().getId()+"";
			s[i+3][1] = p.get(i).getWorker().getCaption();
			for (int j = 0; j < c.size(); j++) {
				id.setCategoryId(c.get(j).getId());
				ProjectWbsManhour data = pwms.findById(id);
				if (data != null) {
					s[i + 3][j*2 + 2] = data.getManhour()+"";
					s[i + 3][j*2 + 3] = data.getOvertime()+"";
//					totals[j*2+2] = totals[j*2+2] + data.getManhour();
//					totals[j*2+3] = totals[j*2+3] + data.getManhour();
				} else {
					s[i + 3][j*2 + 2] = "";
					s[i + 3][j*2 + 3] = "";
				}
			}
		}
//		s[p.size()+3][0] = Labels.TOTAL;
//		for (int i = 2; i < totals.length; i++) {
//			s[p.size()+3][i] = totals[i] + "";
//		}
		return s;
	}

//	String catIds = "";
//  catIds = catIds+","+c.get(i).getId();
//	s[1][c.size()+1] = Labels.OVERTIME;
//	s[1][c.size()+2] = Labels.FOREIGN;
//	s[1][c.size()+3] = Labels.LOCALMH;
//	s[1][c.size()+4] = Labels.TRMH;
//			int overtime=0;
//			int foreign=0;
//			int localmh=0;
//			int trmh=0;
//			s[i + 3][j + 2] = "<a href=\"/projectWbsManhour/edit?id=" + id.toString() + "\">" + data.getManhour() + "</a>";
//			overtime = overtime + data.getOvertime();
//			foreign = foreign + data.getForeignMh();
//			localmh = localmh + data.getLocalMh();
//			trmh = trmh + data.getTrMh();
//			s[i + 3][j + 1] = "<a href=\"/projectWbsManhour/new?id=" + id.toString() + "\">" + language.getText(Labels.EDIT) + "</a>";
//			totals[c.size()+1] = totals[c.size()+1] + overtime;
//			totals[c.size()+2] = totals[c.size()+2] + foreign;
//			totals[c.size()+3] = totals[c.size()+3] + localmh;
//			totals[c.size()+4] = totals[c.size()+4] + trmh;
//			s[i + 2][c.size() + 2] = overtime+"";
//			s[i + 2][c.size() + 3] = foreign+"";
//			s[i + 2][c.size() + 4] = localmh+"";
//			s[i + 2][c.size() + 5] = trmh+"";
//			s[i + 2][c.size() + 6] = "";
	
	public void setWbsManhourByDate(int projectId, int teamId, Date date, ProjectWbsManhourService pwms, String[][] list) throws Exception {
		ProjectWbsManhourId id = new ProjectWbsManhourId(projectId, 0, teamId, 0, date);
		for (int i = 3; i < list.length; i++) {
			int workerId = Integer.parseInt(list[i][0]);
			id.setWorkerId(workerId);
			for (int j = 2; j < list.length/2; j++) {
				int categoryId = Integer.parseInt(list[i][j]);
				id.setCategoryId(categoryId);
				ProjectWbsManhour data = pwms.findById(id);
				data.setManhour(Short.parseShort(list[i][j*2]));
				data.setOvertime(Short.parseShort(list[i][j*2+1]));
				pwms.save(data);
			}
		}
	}

	@Override
	public ProjectTeam newEntityWithId(String strId) {
		ProjectTeam entity = new ProjectTeam();
		entity.setId(strToId(strId));
		return entity;
	}

	@Override
	public String parentLink(ProjectTeam entity) {
		if (entity == null) return null;
		return Project.ROOTMAPPING + "/show?id=" + entity.getId().getProjectId();
	}

	@Override
	public String idAsStr(ProjectTeam entity) {
		if (entity != null)
			return entity.getId().toString();
		else
			return null;
	}

}
