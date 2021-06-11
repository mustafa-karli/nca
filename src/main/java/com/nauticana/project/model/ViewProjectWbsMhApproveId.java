package com.nauticana.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

@Embeddable
public class ViewProjectWbsMhApproveId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int projectId;
	private int categoryId;
	private int teamId;
	private Date activityDate;

	public ViewProjectWbsMhApproveId() {
	}

	public ViewProjectWbsMhApproveId(int projectId, int categoryId, int teamId, Date activityDate) {
		this.projectId = projectId;
		this.categoryId = categoryId;
		this.teamId = teamId;
		this.activityDate = activityDate;
	}

	public ViewProjectWbsMhApproveId(String keys) {
		String[] s = keys.split(",");
		this.projectId = Integer.parseInt(s[0]);
		this.categoryId = Integer.parseInt(s[1]);
		this.teamId = Integer.parseInt(s[2]);
	}

	
	@Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)
	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)
	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)
	public int getTeamId() {
		return this.teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "ACTIVITY_DATE", nullable = false, length = 7)
	public Date getActivityDate() {
		return this.activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other)) return true;
		if ((other == null)) return false;
		if (!(other instanceof ViewProjectWbsMhApproveId)) return false;
		ViewProjectWbsMhApproveId castOther = (ViewProjectWbsMhApproveId) other;
		return (this.getProjectId() == castOther.getProjectId())
			&& (this.getCategoryId() == castOther.getCategoryId())
			&& (this.getTeamId() == castOther.getTeamId())
			&& ((this.getActivityDate() == castOther.getActivityDate())	|| (this.getActivityDate() != null && castOther.getActivityDate() != null && this.getActivityDate().equals(castOther.getActivityDate())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProjectId();
		result = 37 * result + this.getCategoryId();
		result = 37 * result + this.getTeamId();
		result = 37 * result + (getActivityDate() == null ? 0 : this.getActivityDate().hashCode());
		return result;
	}
	
	public String toString() {
		return projectId + "," + categoryId + "," + teamId + "," + activityDate;
	}

}
