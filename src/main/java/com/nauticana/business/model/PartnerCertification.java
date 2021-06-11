package com.nauticana.business.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.Data;

@Data
@Entity
@Table(name = "PARTNER_CERTIFICATION")
public class PartnerCertification implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String SEQUENCE_NAME = "PARTNER_CERTIFICATION_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "PARTNER_CERTIFICATION_ID", unique = true, nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUSINESS_PARTNER_ID", nullable = false)
	private BusinessPartner businessPartner;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CERTIFICATION_TYPE", nullable = false)
	private PartnerCertificationType partnerCertificationType;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ISSUE_DATE", nullable = false, length = 26)
	private Date issueDate;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRE_DATE", nullable = false, length = 26)
	private Date expireDate;

	@Column(name = "LEVEL", nullable = false, length = 20)
	private String level;

	@Column(name = "VALIDATION_KEY", nullable = false, length = 80)
	private String validationKey;
}
