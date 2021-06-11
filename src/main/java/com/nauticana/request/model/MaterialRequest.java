package com.nauticana.request.model;

import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nauticana.basis.utils.Labels;
import com.nauticana.personnel.model.Organization;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "MATERIAL_REQUEST")
public class MaterialRequest implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   SEQUENCE_NAME = "MATERIAL_REQUEST_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "MATERIAL_REQUEST_ID", unique = true, nullable = false)
	private int id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false)
	private Organization organization;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "USERNAME", nullable = false, length = 30)
	private String username;

	@Column(name = "PURPOSE", nullable = false, length = 1)
	private String purpose;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REQUEST_DATE", nullable = false, length = 26)
	private Date requestDate;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DUE_DATE", nullable = false, length = 26)
	private Date dueDate;

	@Column(name = "STATUS", nullable = false, length = 1)
	private String status;

	@Column(name = "DESCRIPTION", length = 250)
	private String description;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialRequest")
	private Set<MaterialRequestItem> materialRequestItems = new HashSet<MaterialRequestItem>(0);
}
