package com.nauticana.personnel.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;
import com.nauticana.maintenance.model.EquipmentAssignment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "PERSON")
public class Person implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String SEQUENCE_NAME = "PERSON_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "PERSON_ID", unique = true, nullable = false)
	private int id;

	@Column(name = "FIRST_NAME", nullable = false, length = 40)
	private String firstName;

	@Column(name = "MIDDLE_NAME", length = 40)
	private String middleName;

	@Column(name = "LAST_NAME", nullable = false, length = 40)
	private String lastName;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BIRTHDAY", nullable = false, length = 26)
	private Date birthday;

	@Column(name = "GENDER", nullable = false, length = 1)
	private String gender;

	@Column(name = "NATIONALITY", nullable = false, length = 2)
	private String nationality;

	@Column(name = "GOVERNMENT_ID", length = 20)
	private String governmentId;

	@Column(name = "PERSONAL_MAIL", length = 80)
	private String personalMail;

	@Column(name = "CELL_PHONE", length = 20)
	private String cellPhone;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	private Set<EquipmentAssignment> equipmentAssignments = new HashSet<EquipmentAssignment>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	private Set<UserAccountOwner> userAccountOwners = new HashSet<UserAccountOwner>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	private Set<CreditCard> creditCards = new HashSet<CreditCard>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	private Set<Employee> employees = new HashSet<Employee>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	private Set<PersonQualification> personQualifications = new HashSet<PersonQualification>(0);
}
