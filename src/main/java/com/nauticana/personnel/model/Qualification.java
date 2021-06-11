package com.nauticana.personnel.model;

import java.math.BigDecimal;
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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = Qualification.TABLE_NAME)
public class Qualification implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "qualification";
	public  static final String   TABLE_NAME  = "QUALIFICATION";
	public  static final String   SEQUENCE_NAME = "QUALIFICATION_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "QUALIFICATION_ID", unique = true, nullable = false)
	private int id;

	@Column(name = "QUALIFICATION_TYPE", nullable = false, length = 12)
	private String qualificationType;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String caption;

	@Column(name = "GRADE_BASE", nullable = false, precision = 10)
	private BigDecimal gradeBase;

	@Column(name = "VALIDITY")
	private Integer validity;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "qualification")
	private Set<PersonQualification> personQualifications = new HashSet<PersonQualification>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "qualification")
	private Set<PositionQualification> positionQualifications = new HashSet<PositionQualification>(0);
}
