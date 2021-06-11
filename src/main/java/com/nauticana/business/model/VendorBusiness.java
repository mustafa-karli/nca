package com.nauticana.business.model;

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

import lombok.Data;

@Data
@Entity
@Table(name = VendorBusiness.TABLE_NAME)
public class VendorBusiness implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "vendorBusiness";
	public  static final String   TABLE_NAME  = "VENDOR_BUSINESS";

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "vendorId", column = @Column(name = "BUSINESS_PARTNER_ID", nullable = false)),
			@AttributeOverride(name = "nodeId", column = @Column(name = "NODE_ID", nullable = false)) })
	private VendorBusinessId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUSINESS_PARTNER_ID", nullable = false, insertable = false, updatable = false)
	private Vendor vendor;

	@Column(name = "BEGDA", nullable = false, length = 26)
	private Date begda;
}
