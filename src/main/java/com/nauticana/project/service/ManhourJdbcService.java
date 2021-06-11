package com.nauticana.project.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.basis.utils.FieldType;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.Utils;
import com.nauticana.personnel.model.Employee;
import com.nauticana.personnel.model.Person;
import com.nauticana.personnel.service.EmployeeService;
import com.nauticana.project.model.Category;
import com.nauticana.project.model.ReportProjectWbsStatus;
import com.nauticana.project.model.ViewProgressBrief;
import com.nauticana.project.model.ViewProjectCategory;
import com.nauticana.project.model.ViewProjectWbsMhApprove;
import com.nauticana.project.model.ViewProjectWbsQtyApprove;
import com.nauticana.project.model.ViewWbsQuantity;
import com.nauticana.project.model.ViewWbsQuantityId;
import com.nauticana.project.model.ViewWbsTeamStatus;
import com.nauticana.project.repository.ManhourJbdcRepository;

@Service
public class ManhourJdbcService {

	@Autowired
	private ManhourJbdcRepository r;
	
	@Autowired
	private CategoryService cs;
	
	@Autowired
	private EmployeeService employeeService;
	
	public List<ViewProjectCategory> findAllCategories(int projectId, String langcode) {
		List<ViewProjectCategory> l = r.viewProjectCategory(projectId, -1);
		translateCategory1(l, langcode);
		return l;
	}
	
	public String findAllCategoryHtml(int projectId, int projectFilter, String clsName, String[] links, String[] caps, String langcode) {
		int level = 1;
		boolean endLi = false;
		
		String s = "<UL class=\"" + clsName + "\">";
		List<ViewProjectCategory> cat = r.viewProjectCategory(projectId, projectFilter);
		translateCategory1(cat, langcode);
		ViewProjectCategory[] pr = new ViewProjectCategory[10];
		
		for (int i = 0; i< cat.size(); i++) {
			ViewProjectCategory c = cat.get(i);
			pr[c.getCatLevel()] = c;
			if (c.getCatLevel()>1)
				pr[c.getCatLevel()-1].hasChild = true;
			if (!Utils.emptyStr(c.getProjectId())) {
				c.checked = "checked=\"checked\"";
				for (int j = 1; j < c.getCatLevel(); j++)
					pr[j].expended = "checked=\"checked\"";
			} 
			if (links != null) {
				for (int j = 0; j < links.length; j++) {
					c.links = c.links + " &nbsp; <a href=\"#\" onClick=\"doAjaxGet('" + links[j] + c.getCategoryId() + "');\">" + caps[j] + "</a>"; 
				}
			}
			
		}
		
		for (ViewProjectCategory c : cat) {
			if (level < c.getCatLevel()) {
				s = s + "\n <UL>\n";
				endLi = false;
			}
			for (int i = c.getCatLevel(); i < level; i++) {
				s = s + "  </LI>\n </UL>\n";
				endLi = true;
			}
			if (endLi) s = s + "  </LI>\n";

			if (c.hasChild)
				s = s + "<LI><input type=checkbox name=\"Arrow\" id=\"" + c.getTreeCode() + "\" " + c.expended + "/><label><input type=checkbox name=\"WBS\" value=\"" + c.getCategoryId() + "\"" + c.checked + " /><span></span></label><label for=\"" + c.getTreeCode() + "\">" + c.getTreeCode() + " &nbsp; " + c.getCaption() + "</label>" + c.links;
			else
				s = s + "<LI><label><input type=checkbox name=\"WBS\" value=\"" + c.getCategoryId() + "\"" + c.checked + " /><span></span></label><label> &nbsp; &nbsp; " + c.getTreeCode() + " &nbsp; " + c.getCaption() + "</label>" + c.links;
			
//			if (Utils.emptyStr(c.getProjectId()))
//				s = s + "  <LI> " + "<input type=checkbox class=\"flat-red\" name=\"WBS\" value=\"" + c.getCategoryId() + "\"/> <b> " + c.getTreeCode() + " </b> " + c.getCaption(); 
//			else
//				s = s + "  <LI> " + "<input type=checkbox class=\"flat-red\" name=\"WBS\" value=\"" + c.getCategoryId() + "\" checked=\"checked\"/> <b> " + c.getTreeCode() + " </b> " + c.getCaption();
			endLi = true;
			level = c.getCatLevel();
		}
		for (int i = 1; i < level; i++) {
			s = s + "  </LI>\n </UL>\n";
			endLi = true;
		}
		if (endLi) s = s + "  </LI>\n";
		return s + "</UL>\n";
	}
	
	public List<ViewProjectCategory> findAllCategories(int projectId, int teamId, String langcode) {
		List<ViewProjectCategory> l = r.teamCategory(projectId, teamId);
		translateCategory1(l, langcode);
		return l;
	}
	
	public String findAllCategoryHtml(int projectId, int teamId, String langcode) {
		String s = "";
		List<ViewProjectCategory> l = r.teamCategory(projectId, teamId);
		translateCategory1(l, langcode);
		for (ViewProjectCategory c : l) {
			if (Utils.emptyStr(c.getProjectId()))
				s = s + "  <p> " + "<input type=checkbox class=\"flat-red\" name=\"WBS\" value=\"" + c.getCategoryId() + "\"/> <b> " + c.getTreeCode() + " </b> " + c.getCaption() + "</p>"; 
			else
				s = s + "  <p> " + "<input type=checkbox class=\"flat-red\" name=\"WBS\" value=\"" + c.getCategoryId() + "\" checked=\"checked\"/> <b> " + c.getTreeCode() + " </b> " + c.getCaption() + "</p>"; 
		}
		return s;
	}
	
	public int nextTeamId(int projectId) {
		return r.nextTeamId(projectId);
	}

	public Person localPersonnel(int client, int personId) {
		ArrayList<String>  fields  = new ArrayList<String>();
		ArrayList<String>  filters = new ArrayList<String>();
		ArrayList<Integer> types  = new ArrayList<Integer>();
		fields.add("OWNER_ID");
		fields.add("PERSON_ID");
		filters.add(client+"");
		filters.add(personId+"");
		types.add(FieldType.T_INT);
		types.add(FieldType.T_INT);
		try {
			List<Employee> records = employeeService.search(fields, filters, types);
			return records.get(0).getPerson();
		} catch (ParseException e) {
			return null;
		}
	}

	public List<ViewWbsQuantity> aggrProjectWbsQuantity(int projectId, String langcode) {
		List<ViewWbsQuantity> l = r.aggrProjectWbsQuantity(projectId);
		translateCategory6(l, langcode);
		return l;
	}

	private ViewWbsQuantity fromCategory(int projectId, int categoryId) {
		Category c = cs.findById(categoryId);
		ViewWbsQuantityId id = new ViewWbsQuantityId(projectId, categoryId);
		return new ViewWbsQuantity(id, null, c.getParentId(), c.getTreeCode(), c.getCaption(), null, 0, 0, 0, 0, 0, 0);
	}
	
	public List<ViewWbsQuantity> aggrProjectWbsQuantityTree(int projectId, String langcode) {
		List<ViewWbsQuantity> wbsQuantityList = r.aggrProjectWbsQuantity(projectId);
		ArrayList<ViewWbsQuantity> records = new ArrayList<ViewWbsQuantity>();
		Map<Integer, ViewWbsQuantity> m = new HashMap<Integer, ViewWbsQuantity>();
		for (ViewWbsQuantity v : wbsQuantityList) {
			ViewWbsQuantity parent = m.get(v.getParentId());
			if (parent == null) {
				parent = fromCategory(projectId, v.getParentId());
				m.put(v.getParentId(), parent);
				parent.addChild(v);
				if (parent.getParentId() == -1) records.add(parent);
				ViewWbsQuantity walker = parent;
				while (walker != null && walker.getParentId() != -1) {
					if (m.get(walker.getParentId()) == null) {
						walker.setParent(fromCategory(projectId, walker.getParentId()));
						m.put(walker.getParentId(), walker.getParent());
						walker.getParent().getChildren().add(walker);
						if (walker.getParent().getParentId() == -1) records.add(walker);
					}
					walker = walker.getParent();
				}
			}
			while (parent != null) {
				parent.setSumManhour(parent.getSumManhour() + v.getSumManhour());
				parent.setPupWorkforce(parent.getPupWorkforce() + v.getPupWorkforce());
				parent.setWorkforce(parent.getWorkforce() + v.getWorkforce());
				parent = parent.getParent();
			}
		}
		translateCategory6(records, langcode);
		return records;
	}

	private void calcMh(ArrayList<ViewProgressBrief> records) {
		for (ViewProgressBrief r : records) {
			calcMh(r.getChildren());
			for (ViewProgressBrief c : r.getChildren()) {
				r.setWorkforce(r.getWorkforce() + c.getWorkforce());
				r.setPupWorkforce(r.getPupWorkforce() + c.getPupWorkforce());
				r.setSumManhour(r.getSumManhour() + c.getSumManhour());
				r.setEarnedManhour(r.getEarnedManhour() + c.getEarnedManhour());
				r.setEstimatedCompMH(r.getEstimatedCompMH() + c.getEstimatedCompMH());
				r.setRemainingMH(r.getRemainingMH() + c.getRemainingMH());
			}
			if (r.getSumManhour() != 0) r.setPerformans(r.getEarnedManhour() / r.getSumManhour()); else r.setPerformans(0);
			if (r.getPupWorkforce() != 0) r.setDeviation(1 - r.getEstimatedCompMH() / r.getPupWorkforce()); else r.setDeviation(0);
			//Progress = earned manhour / Pup Workforce
			if (r.getPupWorkforce() != 0 ) r.setProgress(r.getEarnedManhour() /  r.getPupWorkforce()); else r.setDeviation(0);

		}
	}
	
	public List<ViewProgressBrief> getProjectBrief(int projectId, String langcode) {
		List<ViewProgressBrief> l = r.getProjectProgressBrief(projectId);
		translateCategory2(l, langcode);
		Map<Integer, ViewProgressBrief> m = new HashMap<Integer, ViewProgressBrief>();
		ArrayList<ViewProgressBrief> tops = new ArrayList<ViewProgressBrief>();
		for (ViewProgressBrief v : l) {
			m.put(v.getId(), v);
		}
		for (ViewProgressBrief v : l) {
			ViewProgressBrief parent = m.get(v.getParentId());
			v.setParent(parent);
			if (parent == null)
				tops.add(v);
			else
				parent.addChild(v);
		}
		calcMh(tops);
		return l;
	}
	
	public Date getMinInitialDate(int projectId, Date begda, Date endda ) {
		return r.getStatusMinDate(projectId, begda, endda, Labels.INITIAL);
	}
	
	public Date getMinApprovedDate(int projectId, Date begda, Date endda ) {
		return r.getStatusMinDate(projectId, begda, endda, Labels.APPROVED);
	}
	
	public List<ViewWbsQuantity> allProjectWbsQuantity(String langcode) {
		List<ViewWbsQuantity> l = r.allProjectWbsQuantity();
		translateCategory6(l, langcode);
		return l;
	}

	public ViewWbsQuantity singleProjectWbsQuantity(int projectId, int categoryId) {
		return r.singleProjectWbsQuantity(projectId, categoryId);
	}

	public List<ViewWbsTeamStatus> viewWbsTeamStatus(int projectId, Date begda, String langcode) {
		List<ViewWbsTeamStatus> l = r.viewWbsTeamStatus(projectId, begda);
		translateCategory7(l, langcode);
		return l;
	}

	public List<ViewWbsTeamStatus> viewWbsTeamStatus(int projectId, int teamId, Date begda, String langcode) {
		List<ViewWbsTeamStatus> l =  r.viewWbsTeamStatus(projectId, teamId, begda);
		translateCategory7(l, langcode);
		return l;
	}
	
	public int addProjectApproval(int projectId, String username, String action) {
		return r.addProjectApproval(projectId, username, action);
	}
	
	public List<ReportProjectWbsStatus> getProjectProgressInitial(int projectId, Date begda, Date endda, String langcode) {
		List<ReportProjectWbsStatus> l = r.getProjectProgressInitial(projectId, begda, endda);
		translateCategory3(l, langcode);
		return l;
	}
	
	public List<ReportProjectWbsStatus> getReportProjectWbsStatusApproved(int projectId, Date begda, Date endda, String langcode) {
		List<ReportProjectWbsStatus> l = r.getReportProjectWbsStatusApproved(projectId, begda, endda);
		translateCategory3(l, langcode);
		return l;
	}
	
	public List<ReportProjectWbsStatus> getReportProjectWbsStatusAll(int projectId, Date begda, Date endda, String langcode) {
		List<ReportProjectWbsStatus> l = r.getReportProjectWbsStatusAll(projectId, begda, endda);
		translateCategory3(l, langcode);
		return l;
	}
	
	public List<ReportProjectWbsStatus> getReportProjectWbsStatusForTeam(int projectId, int teamId, Date begda, Date endda, String langcode) {
		List<ReportProjectWbsStatus> l = r.getReportProjectWbsStatusForTeam(projectId, teamId, begda, endda);
		translateCategory3(l, langcode);
		return l;
	}

	public List<ReportProjectWbsStatus> getReportProjectWbsStatusForSubcontractor(int projectId, int subcontractorId, Date begda, Date endda, String langcode) {
		List<ReportProjectWbsStatus> l = r.getReportProjectWbsStatusForSubcontractor(projectId, subcontractorId, begda, endda);
		translateCategory3(l, langcode);
		return l;
	}

	public List<ViewProjectWbsMhApprove> getProjectWbsMhApprove(int projectId, String langcode) {
		List<ViewProjectWbsMhApprove> l = r.getProjectWbsMhApprove(projectId);
		translateCategory4(l, langcode);
		return l;
	}

	public List<ViewProjectWbsQtyApprove> getProjectWbsQtyApprove(int projectId, String langcode) {
		List<ViewProjectWbsQtyApprove> l = r.getProjectWbsQtyApprove(projectId);
		translateCategory5(l, langcode);
		return l;
	}

	public List<String> getProjectTeamTemplate(int projectId, int teamId, Date activityDate) {
		return r.getProjectTeamTemplate(projectId, teamId, activityDate);
	}

	public int setProjectWbsQtyStatus (int projectId, Date begda, Date endda, String status) {
		return r.setProjectWbsQtyStatus(projectId, begda, endda, status);
	}

	public int setProjectWbsMhStatus (int projectId, Date begda, Date endda, String status) {
		return r.setProjectWbsMhStatus(projectId, begda, endda, status);
	}
	
	public void translateCategory1(List<ViewProjectCategory> data, String langcode) {
		Map<Integer, String> t = r.categoryTranslation(langcode);
		for(ViewProjectCategory record : data) {
			String caption = t.get(record.getCategoryId());
			if (caption != null)
				record.setCaption(caption);
		}
	}

	public void translateCategory2(List<ViewProgressBrief> data, String langcode) {
		Map<Integer, String> t = r.categoryTranslation(langcode);
		for(ViewProgressBrief record : data) {
			String caption = t.get(record.getId());
			if (caption != null)
				record.setCaption(caption);
		}
	}

	public void translateCategory3(List<ReportProjectWbsStatus> data, String langcode) {
		Map<Integer, String> t = r.categoryTranslation(langcode);
		for(ReportProjectWbsStatus record : data) {
			String caption = t.get(record.getCategoryId());
			if (caption != null)
				record.setCatCaption(caption);
		}
	}

	public void translateCategory4(List<ViewProjectWbsMhApprove> data, String langcode) {
		Map<Integer, String> t = r.categoryTranslation(langcode);
		for(ViewProjectWbsMhApprove record : data) {
			String caption = t.get(record.getId().getCategoryId());
			if (caption != null)
				record.setCategoryCaption(caption);
		}
	}

	public void translateCategory5(List<ViewProjectWbsQtyApprove> data, String langcode) {
		Map<Integer, String> t = r.categoryTranslation(langcode);
		for(ViewProjectWbsQtyApprove record : data) {
			String caption = t.get(record.getId().getCategoryId());
			if (caption != null)
				record.setCategoryCaption(caption);
		}
	}

	public void translateCategory6(List<ViewWbsQuantity> data, String langcode) {
		Map<Integer, String> t = r.categoryTranslation(langcode);
		for(ViewWbsQuantity record : data) {
			String caption = t.get(record.getId().getCategoryId());
			if (caption != null)
				record.setCatCaption(caption);
		}
	}

	public void translateCategory7(List<ViewWbsTeamStatus> data, String langcode) {
		Map<Integer, String> t = r.categoryTranslation(langcode);
		for(ViewWbsTeamStatus record : data) {
			String caption = t.get(record.getId().getCategoryId());
			if (caption != null)
				record.setCatCaption(caption);
		}
	}

}
