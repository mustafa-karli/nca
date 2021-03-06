package com.nauticana.purchase.model;

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

import com.nauticana.basis.utils.Labels;
import com.nauticana.personnel.model.Organization;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = PurchaseDelivery.TABLE_NAME)
public class PurchaseDelivery implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "purchaseDelivery";
	public  static final String   TABLE_NAME  = "PURCHASE_DELIVERY";
	public  static final String   SEQUENCE_NAME = "PURCHASE_DELIVERY_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "DELIVERY_ID", unique = true, nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false)
	private Organization organization;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DELIVERY_DATE", nullable = false, length = 26)
	private Date deliveryDate;

	@Column(name = "INVOICE_ID", length = 20)
	private String invoiceId;

	@Column(name = "USERNAME", nullable = false, length = 30)
	private String username;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseDelivery")
	private Set<PurchaseDeliveryLine> purchaseDeliveryLines = new HashSet<PurchaseDeliveryLine>(0);
}
