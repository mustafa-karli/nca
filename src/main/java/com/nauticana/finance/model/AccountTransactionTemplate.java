package com.nauticana.finance.model;

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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = AccountTransactionTemplate.TABLE_NAME)
public class AccountTransactionTemplate implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ROOTMAPPING = "accountTransactionTemplate";
	public static final String TABLE_NAME = "ACCOUNT_TRANSACTION_TEMPLATE";
	public static final String SEQUENCE_NAME = "ACCOUNT_TX_TEMPLATE_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "TEMPLATE_ID", unique = true, nullable = false)
	private int id;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "CAPTION", nullable = false, length = 30)
	private String caption;

	@Column(name = "DOCUMENT_TYPE", nullable = false, length = 2)
	private String documentType;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountTransactionTemplate")
	private Set<AccountTxTemplateItem> accountTxTemplateItems = new HashSet<AccountTxTemplateItem>(0);
}
