package com.nauticana.project.model;

import java.util.Date;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.Data;

@Data
@Entity
@Table(name = ProjectWbsQuantity.TABLE_NAME)
public class ProjectWbsQuantity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String   ROOTMAPPING = "projectWbsQuantity";
	public static final String   TABLE_NAME  = "PROJECT_WBS_QUANTITY";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "PROJECT_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "categoryId", column = @Column(name = "CATEGORY_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "begda", column = @Column(name = "BEGDA", nullable = false)) })
	private ProjectWbsQuantityId id;
	
	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Column(name = "ENDDA", nullable = false)
	private Date endda;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID", nullable = false, insertable = false, updatable = false) })
	private ProjectWbs projectWbs;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID", nullable = false, insertable = false, updatable = false) })
	private ProjectTeam projectTeam;

	@Column(name = "QUANTITY", nullable = false, precision = 8)
	private float quantity;

	@Column(name = "STATUS", nullable = false, length = 20)
	private String status;
}
