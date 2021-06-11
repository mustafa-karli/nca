package com.nauticana.business.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nauticana.helpdesk.model.ServiceLevelAgreement;
import com.nauticana.sales.model.SalesAccountBalance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "BUSINESS_PARTNER")
public class BusinessPartner implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String SEQUENCE_NAME = "BUSINESS_PARTNER_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "BUSINESS_PARTNER_ID", unique = true, nullable = false)
	private int id;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String caption;

	@Column(name = "WEB_ADDRESS", length = 80)
	private String webAddress;

	@Column(name = "HQ_COUNTRY", length = 2)
	private String hqCountry;

	@Column(name = "TAX_CENTER", length = 80)
	private String taxCenter;

	@Column(name = "TAX_NUMBER", length = 20)
	private String taxNumber;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "businessPartner")
	private ApplicationConfig applicationConfig;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "businessPartner")
	private BusinessOwner businessOwner;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "businessPartner")
	private Vendor vendor;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "businessPartner")
	private Customer customer;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "businessPartner")
	private SalesAccountBalance salesAccountBalance;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "businessPartner")
	private Subcontractor subcontractor;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessPartner")
	private Set<PartnerCertification> partnerCertifications = new HashSet<PartnerCertification>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessPartner")
	@OrderBy
	private Set<PartnerAddress> partnerAddresses = new LinkedHashSet<PartnerAddress>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessPartner")
	private Set<ServiceLevelAgreement> serviceLevelAgreements = new HashSet<ServiceLevelAgreement>(0);
}
