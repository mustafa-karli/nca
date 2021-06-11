package com.nauticana.finance.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "ACCOUNT_TRANSACTION")
public class AccountTransaction implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String SEQUENCE_NAME = "ACCOUNT_TRANSACTION_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "TRANSACTION_ID", unique = true, nullable = false)
	private long id;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "CURRENCY", nullable = false, length = 2)
	private String currency;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TX_TIMESTAMP", nullable = false, length = 26)
	private Date txTimestamp;

	@Column(name = "TX_YEAR", nullable = false)
	private short txYear;

	@Column(name = "TX_QUARTER", nullable = false)
	private short txQuarter;

	@Column(name = "TX_MONTH", nullable = false)
	private short txMonth;

	@Column(name = "TX_WEEK", nullable = false)
	private short txWeek;

	@Column(name = "TX_DAY", nullable = false)
	private short txDay;

	@Column(name = "DOCUMENT_TYPE", length = 2)
	private String documentType;

	@Column(name = "DOCUMENT_ID")
	private Integer documentId;

	@Column(name = "REVERSE_TRANSACTION")
	private Long reverseTransaction;

	@Column(name = "DESCRIPTION", length = 200)
	private String description;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountTransaction")
	private Set<AccountTransactionItem> accountTransactionItems = new HashSet<AccountTransactionItem>(0);
}
