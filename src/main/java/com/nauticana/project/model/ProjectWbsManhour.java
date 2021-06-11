package com.nauticana.project.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = ProjectWbsManhour.TABLE_NAME)
public class ProjectWbsManhour implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String   ROOTMAPPING = "projectWbsManhour";
	public static final String   TABLE_NAME  = "PROJECT_WBS_MANHOUR";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "categoryId", column = @Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "teamId", column = @Column(name = "TEAM_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "workerId", column = @Column(name = "WORKER_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "activityDate", column = @Column(name = "ACTIVITY_DATE", nullable = false, length = 7)) })
	private ProjectWbsManhourId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "WORKER_ID", referencedColumnName = "WORKER_ID", nullable = false, insertable = false, updatable = false) })
	private ProjectTeamPerson projectTeamPerson;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID", nullable = false, insertable = false, updatable = false) })
	private ProjectWbs projectWbs;

	@Column(name = "MANHOUR", nullable = false, precision = 4, scale = 0)
	private short manhour;

	@Column(name = "OVERTIME", precision = 4, scale = 0)
	private short overtime;

	@Column(name = "LOCAL_MH", precision = 4, scale = 0)
	private short localMh;

	@Column(name = "FOREIGN_MH", precision = 4, scale = 0)
	private short foreignMh;

	@Column(name = "TR_MH", precision = 4, scale = 0)
	private short trMh;

	@Column(name = "STATUS", nullable = false, length = 20)
	private String status;
}
