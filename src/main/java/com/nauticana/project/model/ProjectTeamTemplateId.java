package com.nauticana.project.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTeamTemplateId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)
	private int projectId;

	@Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)
	private int categoryId;

	@Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)
	private int teamId;

	public ProjectTeamTemplateId(String keys) {
		String[] s = keys.split(",");
		this.projectId = Integer.parseInt(s[0]);
		this.categoryId = Integer.parseInt(s[1]);
		this.teamId = Integer.parseInt(s[2]);
	}

	public String toString() {
		return projectId + "," + categoryId + "," + teamId;
	}

}
