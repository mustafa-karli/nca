package com.nauticana.business.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nauticana.personnel.model.Organization;
import com.nauticana.purchase.model.PurchaseOrder;
import com.nauticana.purchase.model.RequestForProposal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = PartnerAddress.TABLE_NAME)
public class PartnerAddress implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "partnerAddress";
	public  static final String   TABLE_NAME  = "PARTNER_ADDRESS";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "businessPartnerId", column = @Column(name = "BUSINESS_PARTNER_ID", nullable = false)),
			@AttributeOverride(name = "addressId", column = @Column(name = "ADDRESS_ID", nullable = false)) })
	private PartnerAddressId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUSINESS_PARTNER_ID", nullable = false, insertable = false, updatable = false)
	private BusinessPartner businessPartner;

	@Column(name = "ADDRESS_TYPE", nullable = false, length = 1)
	private String addressType;

	@Column(name = "STREET", nullable = false, length = 80)
	private String street;

	@Column(name = "CITY", nullable = false, length = 20)
	private String city;

	@Column(name = "STATE", length = 20)
	private String state;

	@Column(name = "COUNTRY", nullable = false, length = 2)
	private String country;

	@Column(name = "PHONE", length = 20)
	private String phone;

	@Column(name = "LONGITUDE", precision = 64, scale = 0)
	private Double longitude;

	@Column(name = "LATITUDE", precision = 64, scale = 0)
	private Double latitude;

	@Column(name = "ALTITUDE", precision = 64, scale = 0)
	private Double altitude;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "partnerAddress")
	private Set<Organization> organizations = new HashSet<Organization>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "partnerAddresses")
	private Set<PurchaseOrder> purchaseOrders = new HashSet<PurchaseOrder>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "deliveryAddress")
	private Set<RequestForProposal> requestForProposals = new HashSet<RequestForProposal>(0);
}
