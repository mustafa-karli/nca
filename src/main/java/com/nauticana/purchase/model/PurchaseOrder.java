package com.nauticana.purchase.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;
import com.nauticana.business.model.PartnerAddress;
import com.nauticana.personnel.model.Organization;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = PurchaseOrder.TABLE_NAME)
public class PurchaseOrder implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "purchaseOrder";
	public  static final String   TABLE_NAME  = "PURCHASE_ORDER";
	public  static final String   SEQUENCE_NAME = "PURCHASE_ORDER_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "PURCHASE_ORDER_ID", unique = true, nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false)
	private Organization organization;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "VENDOR_ID", nullable = false)
	private int vendorId;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORDER_DATE", nullable = false, length = 26)
	private Date orderDate;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DUE_DATE", length = 26)
	private Date dueDate;

	@Column(name = "STATUS", nullable = false, length = 1)
	private String status;

	@Column(name = "USERNAME", nullable = false, length = 30)
	private String username;

	@Column(name = "DESCRIPTION", length = 250)
	private String description;

	@Column(name = "DISCOUNT", precision = 10)
	private BigDecimal discount;

	@Column(name = "ADVANCE_PAYMENT", precision = 10)
	private BigDecimal advancePayment;

	@Column(name = "ONLINE_ORDER", length = 1)
	private String onlineOrder;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "PURCHASE_DELIVERY_ADDRESS",
	joinColumns = {
			@JoinColumn(name = "PURCHASE_ORDER_ID", nullable = false, updatable = false)
		}, inverseJoinColumns = {
				@JoinColumn(name = "BUSINESS_PARTNER_ID", nullable = false, updatable = false),
				@JoinColumn(name = "ADDRESS_ID", nullable = false, updatable = false)
		})
	private Set<PartnerAddress> partnerAddresses = new HashSet<PartnerAddress>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrder")
	@OrderBy("LINE ASC")
	private Set<PurchaseOrderItem> purchaseOrderItems = new LinkedHashSet<PurchaseOrderItem>(0);
}
