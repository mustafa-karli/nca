package com.nauticana.business.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;
import com.nauticana.motifarge.model.ProductPriceCommitment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "VENDOR")
public class Vendor implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "businessPartner"))
	@GeneratedValue(generator = "generator")
	@Column(name = "BUSINESS_PARTNER_ID", unique = true, nullable = false)
	private int							id;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private BusinessPartner				businessPartner;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date						begda;

	@Column(name = "APPROVED", nullable = false, length = 1)
	private String                      approved;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vendor")
	private Set<ProductPriceCommitment>	productPriceCommitments	= new HashSet<ProductPriceCommitment>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vendor")
	private Set<VendorBusiness>	vendorBusinesses	= new HashSet<VendorBusiness>(0);
}
