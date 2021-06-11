package com.nauticana.finance.model;

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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "ACCOUNT_SCHEMA")
public class AccountSchema implements java.io.Serializable {

	public static final String SEQUENCE_NAME = "ACCOUNT_SCHEMA_ID_SEQ";
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "ACCOUNT_SCHEMA_ID", unique = true, nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MASTER_ACCOUNT_CODE", nullable = false)
	private AccountMaster accountMaster;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String caption;

	@Column(name = "CODE", nullable = false, length = 32)
	private String code;

	@Column(name = "ACTIVE", nullable = false, length = 1)
	private String active;

	@Column(name = "COST_CENTER")
	private Integer costCenter;

	@Column(name = "DEFAULT_OBJECT_TYPE", length = 2)
	private String defaultObjectType;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountSchema")
	private Set<AccountTransactionItem> accountTransactionItems = new HashSet<AccountTransactionItem>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountSchema")
	private Set<AccountTxTemplateItem> accountTxTemplateItems = new HashSet<AccountTxTemplateItem>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountSchema")
	private Set<AccountBalance> accountBalances = new HashSet<AccountBalance>(0);
}
