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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = ProjectTeam.TABLE_NAME)
public class ProjectTeam implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String   ROOTMAPPING = "projectTeam";
	public static final String   TABLE_NAME  = "PROJECT_TEAM";
	public static final String[] actions = new String[] { "APPROVE_MANHOUR" };

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "teamId", column = @Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)) })
	private ProjectTeamId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID", nullable = false, insertable = false, updatable = false)
	private Project project;

	@Column(name = "CAPTION", nullable = false, length = 50)
	private String caption;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectTeam")
	@OrderBy("TEAM_LEAD DESC")
	private Set<ProjectTeamPerson> projectTeamPersonnel = new HashSet<ProjectTeamPerson>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToMany
	@OrderBy("TREE_CODE")
	@JoinTable(name="PROJECT_TEAM_TEMPLATE",
		joinColumns= {
			@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID", nullable = false, insertable = false, updatable = false)},
		inverseJoinColumns=@JoinColumn(name="CATEGORY_ID"))
	private Set<Category> projectTeamCategory = new HashSet<Category>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OrderBy("BEGDA DESC")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectTeam")
	private Set<ProjectWbsQuantity> projectWbsQuantities = new HashSet<ProjectWbsQuantity>(0);
}
