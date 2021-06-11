package com.nauticana.personnel.model;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.Data;

@Data
@Entity
@Table(name = PositionAssignment.TABLE_NAME)
public class PositionAssignment implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "positionAssignment";
	public  static final String   TABLE_NAME  = "POSITION_ASSIGNMENT";

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "ownerId", column = @Column(name = "OWNER_ID", nullable = false)),
			@AttributeOverride(name = "organizationId", column = @Column(name = "ORGANIZATION_ID", nullable = false)),
			@AttributeOverride(name = "position", column = @Column(name = "POSITION", nullable = false, length = 20)),
			@AttributeOverride(name = "personId", column = @Column(name = "PERSON_ID", nullable = false)),
			@AttributeOverride(name = "employment", column = @Column(name = "EMPLOYMENT", nullable = false, length = 26)),
			@AttributeOverride(name = "begda", column = @Column(name = "BEGDA", nullable = false, length = 26)) })
	private PositionAssignmentId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PERSON_ID", referencedColumnName = "PERSON_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "EMPLOYMENT", referencedColumnName = "EMPLOYMENT", nullable = false, insertable = false, updatable = false) })
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "OWNER_ID", referencedColumnName = "OWNER_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "POSITION", referencedColumnName = "POSITION", nullable = false, insertable = false, updatable = false) })
	private Position position;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDDA", length = 26)
	private Date endda;

	@Column(name = "REASON", nullable = false, length = 1)
	private String reason;

	@Column(name = "ASSIGNMENT_TYPE", nullable = false, length = 1)
	private String assignmentType;
}
