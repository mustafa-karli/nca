package com.nauticana.purchase.model;

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
import javax.persistence.JoinColumns;
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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "REQUEST_FOR_PROPOSAL")
public class RequestForProposal implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String SEQUENCE_NAME = "RFP_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "RFP_ID", unique = true, nullable = false)
	private int id;

	@Column(name = "PURCHASE_AREA", nullable = false)
	private int purchaseArea;

	@Column(name = "USERNAME", nullable = false, length = 30)
	private String username;

	@Column(name = "CAPTION", nullable = false, length = 250)
	private String caption;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REQUEST_DATE", nullable = false, length = 26)
	private Date requestDate;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_OF_PROPOSAL", nullable = false, length = 26)
	private Date endOfProposal;

	@DateTimeFormat(pattern = Labels.dmyDFStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DELIVERY_DATE", nullable = false, length = 26)
	private Date deliveryDate;

	@Column(name = "CONSORTIUM_ALLOWED", nullable = false, length = 1)
	private String consortiumAllowed;

	@Column(name = "PARTIAL_ALLOWED", nullable = false, length = 1)
	private String partialAllowed;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "OWNER_ID", referencedColumnName = "BUSINESS_PARTNER_ID", nullable = false),
			@JoinColumn(name = "DELIVERY_ADDRESS", referencedColumnName = "ADDRESS_ID", nullable = false) })
	private PartnerAddress deliveryAddress;

	@Column(name = "STATUS", nullable = false, length = 1)
	private String status;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "requestForProposal")
	@OrderBy("LINE")
	private Set<RequestForProposalItem> requestForProposalItems = new LinkedHashSet<RequestForProposalItem>();

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "requestForProposal")
	private Set<RfpPublishment> rfpPublishments = new HashSet<RfpPublishment>(0);
}
