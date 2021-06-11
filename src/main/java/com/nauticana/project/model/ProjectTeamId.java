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
public class ProjectTeamId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)
	private int projectId;

	@Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)
	private int teamId;

	public ProjectTeamId(String keys) {
		String[] s = keys.split(",");
		this.projectId = Integer.parseInt(s[0]);
		this.teamId = Integer.parseInt(s[1]);
	}

	@Override
	public String toString() {
		return this.projectId + "," + this.teamId;
	}
}
