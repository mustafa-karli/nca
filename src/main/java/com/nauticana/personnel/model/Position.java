package com.nauticana.personnel.model;

import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = Position.TABLE_NAME)
public class Position implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "position";
	public  static final String   TABLE_NAME  = "POSITION";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "ownerId", column = @Column(name = "OWNER_ID", nullable = false)),
			@AttributeOverride(name = "organizationId", column = @Column(name = "ORGANIZATION_ID", nullable = false)),
			@AttributeOverride(name = "position", column = @Column(name = "POSITION", nullable = false, length = 20)) })
	private PositionId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false, insertable = false, updatable = false)
	private Organization organization;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "OWNER_ID", referencedColumnName = "OWNER_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "POSITION", referencedColumnName = "POSITION", nullable = false, insertable = false, updatable = false) })
	private PositionType positionType;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDDA", nullable = false, length = 26)
	private Date endda;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
	private Set<PositionQualification> positionQualifications = new HashSet<PositionQualification>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
	private Set<PositionAssignment> positionAssignments = new HashSet<PositionAssignment>(0);
}
