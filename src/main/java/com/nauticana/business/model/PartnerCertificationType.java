package com.nauticana.business.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "PARTNER_CERTIFICATION_TYPE")
public class PartnerCertificationType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CERTIFICATION_TYPE", unique = true, nullable = false, length = 30)
	private String id;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String caption;

	@Column(name = "ISSUER", nullable = false, length = 80)
	private String issuer;

	@Column(name = "VALIDATION_ADDRESS", nullable = false)
	private String validationAddress;

	@Column(name = "VALIDITY", nullable = false)
	private int validity;

	@Column(name = "AREA", nullable = false, length = 30)
	private String area;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "partnerCertificationType")
	private Set<PartnerCertification> partnerCertifications = new HashSet<PartnerCertification>(0);
}
