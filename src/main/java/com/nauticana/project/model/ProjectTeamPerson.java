package com.nauticana.project.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = ProjectTeamPerson.TABLE_NAME)
public class ProjectTeamPerson implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String   ROOTMAPPING = "projectTeamPerson";
	public static final String   TABLE_NAME  = "PROJECT_TEAM_PERSON";
	public static final String[] actions = new String[] { "ADD_EMPLOYEE", "ADD_SUBCONTRACTOR" };

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "teamId", column = @Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "workerId", column = @Column(name = "WORKER_ID", nullable = false, precision = 8, scale = 0)) })
	private ProjectTeamPersonId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKER_ID", nullable = false, insertable = false, updatable = false)
	private Worker worker;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID", nullable = false, insertable = false, updatable = false) })
	private ProjectTeam projectTeam;

	@OrderBy("TEAM_LEAD DESC")
	@Column(name = "TEAM_LEAD", nullable = false, length = 1)
	private byte teamLead;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectTeamPerson")
	private Set<ProjectWbsManhour> projectWbsManhours = new HashSet<ProjectWbsManhour>(0);
}
