package com.nauticana.project.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nauticana.business.model.Subcontractor;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "WORKER")
public class Worker implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "worker";
	public  static final String   SEQUENCE_NAME = "WORKER_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "WORKER_ID", unique = true, nullable = false, precision = 8, scale = 0) @Getter @Setter
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUSINESS_PARTNER_ID") @Getter @Setter
	private Subcontractor subcontractor;

	@OrderBy("CAPTION")
	@Column(name = "CAPTION", nullable = false, length = 50) @Getter @Setter
	private String caption;

	@Column(name = "PERSON_ID", precision = 8, scale = 0) @Getter @Setter
	private Integer personId;

	@Column(name = "CITIZENSHIP", length = 2) @Getter @Setter
	private String citizenShip;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "worker") @Getter @Setter
	private Set<ProjectTeamPerson> projectTeamPersons = new HashSet<ProjectTeamPerson>(0);
}
