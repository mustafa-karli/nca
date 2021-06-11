package com.nauticana.purchase.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "PROPOSAL_TO_RFP")
public class ProposalToRfp implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public  static final String   SEQUENCE_NAME = "PROPOSAL_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "PROPOSAL_ID", unique = true, nullable = false)
	private int id;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "DESCRIPTION", nullable = false, length = 250)
	private String description;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALID_UNTIL", nullable = false, length = 26)
	private Date validUntil;

	@Column(name = "SHIPMENT_BY", nullable = false, length = 1)
	private String shipmentBy;

	@Column(name = "EXTRA_DISCOUNT", nullable = false, precision = 12)
	private BigDecimal extraDiscount;

	@Column(name = "TOTAL_PRICE", nullable = false, length = 80)
	private String totalPrice;

	@Column(name = "CURRENCY", nullable = false, length = 3)
	private String currency;

	@Column(name = "USERNAME", nullable = false, length = 30)
	private String username;

	@Column(name = "RFP_ID", nullable = false)
	private int rfpId;

	@Column(name = "PURCHASE_ORDER_ID", nullable = true)
	private Integer purchaseOrderId;

	@Column(name = "PAYMENT_TYPE", nullable = false, length = 1)
	private String paymentType;

	@Column(name = "PAYMENT_NOTE", nullable = true, length = 250)
	private String paymentNote;

	@Column(name = "DELIVERY_NOTE", nullable = true, length = 250)
	private String deliveryNote;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proposalToRfp")
	@OrderBy("LINE ASC")
	private Set<ProposalToRfpItem> proposalToRfpItems = new LinkedHashSet<ProposalToRfpItem>();

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proposalToRfp")
	@OrderBy("DTIME ASC")
	private Set<ProposalToRfpDialog> proposalToRfpDialogs = new LinkedHashSet<ProposalToRfpDialog>();
}
