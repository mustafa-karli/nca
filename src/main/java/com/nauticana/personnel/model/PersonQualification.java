package com.nauticana.personnel.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.Data;

@Data
@Entity
@Table(name = PersonQualification.TABLE_NAME)
public class PersonQualification implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "personQualification";
	public  static final String   TABLE_NAME  = "PERSON_QUALIFICATION";


	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "personId", column = @Column(name = "PERSON_ID", nullable = false)),
		@AttributeOverride(name = "qualificationId", column = @Column(name = "QUALIFICATION_ID", nullable = false)),
		@AttributeOverride(name = "begda", column = @Column(name = "BEGDA", nullable = false, length = 26)) })
	private PersonQualificationId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID", nullable = false, insertable = false, updatable = false)
	private Person person;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUALIFICATION_ID", nullable = false, insertable = false, updatable = false)
	private Qualification qualification;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDDA", length = 26)
	private Date endda;

	@Column(name = "GRADE", precision = 10)
	private BigDecimal grade;

	@Column(name = "ISSUER", length = 80)
	private String issuer;

	@Column(name = "DEPARTMENT", length = 80)
	private String department;
}
