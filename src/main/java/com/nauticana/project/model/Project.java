package com.nauticana.project.model;

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

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = Project.TABLE_NAME)
public class Project implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "project";
	public  static final String   TABLE_NAME  = "PROJECT";
	public  static final String   SEQUENCE_NAME = "PROJECT_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "PROJECT_ID", unique = true, nullable = false, precision = 8, scale = 0)
	private int     id;

	@Column(name = "CAPTION", nullable = false, length = 50)
	private String  caption;

	@Column(name = "STATUS", nullable = true, length = 20)
	private String  status;

	@Column(name = "CUSTOMER", nullable = true, length = 100)
	private String  customer;

	@Column(name = "COUNTRY", nullable = false, length = 2)
	private String  country;

	@Column(name = "LOCATION", nullable = true, length = 50)
	private String  location;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "CONTRACT_DATE")
	private Date    contractDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "AREA_HANDOVER")
	private Date    areaHandover;

	@Column(name = "DURATION", nullable = false, precision = 8, scale = 0)
	private Integer duration;

	@Column(name = "REVIZED_DURATION", precision = 8, scale = 0)
	private Integer revizedDuration;

	@Column(name = "DURATION_TYPE")
	private String  durationType;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "REVIZED_COMPLETION")
	private Date    revizedCompletion;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "EXPECTED_COMPLETION")
	private Date    expectedCompletion;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "END_OF_WARRANTY")
	private Date    endOfWarranty;

	@Column(name = "CONTRACTED_AMOUNT", precision = 10, scale = 2)
	private Long    contractedAmount;

	@Column(name = "CONTRACT_EXCHANGE", nullable = true, length = 3)
	private String  contractExchange;

	@Column(name = "EXPECTED_COST", precision = 12, scale = 0)
	private Long    expectedCost;

	@Column(name = "ADVANCE_PERCENT", precision = 3, scale = 0)
	private Short   advancePercent;

	@Column(name = "LETTER_OF_ADVANCE", precision = 3, scale = 0)
	private Short   letterOfAdvance;

	@Column(name = "LETTER_OF_WARRANTY", precision = 3, scale = 0)
	private Short   letterOfWarranty;

	@Column(name = "ORGANIZATION_ID", precision = 8, scale = 0)
	private Integer organizationId;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private Set<ProjectTeam> projectTeams = new HashSet<ProjectTeam>(0);
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private Set<ProjectWbs> projectWbses = new HashSet<ProjectWbs>(0);
}
