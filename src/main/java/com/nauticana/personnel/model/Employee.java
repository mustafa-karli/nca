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
@Table(name = Employee.TABLE_NAME)
public class Employee implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "employee";
	public  static final String   TABLE_NAME  = "EMPLOYEE";

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "personId", column = @Column(name = "PERSON_ID", nullable = false)),
		@AttributeOverride(name = "employment", column = @Column(name = "EMPLOYMENT", nullable = false, length = 26)) })
	private EmployeeId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID", nullable = false, insertable = false, updatable = false)
	private Person person;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "POSITION", length = 20)
	private String position;

	@Column(name = "EMAIL_ADDRESS", length = 80)
	private String emailAddress;

	@Column(name = "WORK_PHONE", length = 20)
	private String workPhone;

	@Column(name = "CELL_PHONE", length = 20)
	private String cellPhone;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEPARTURE", length = 26)
	private Date departure;

	@Column(name = "DEPARTURE_TYPE", length = 1)
	private Character departureType;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	private Set<PositionAssignment> positionAssignments = new HashSet<PositionAssignment>(0);
}
