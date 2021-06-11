package com.nauticana.finance.model;

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
@Table(name = "ACCOUNT_TX_TEMPLATE_ITEM")
public class AccountTxTemplateItem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "templateId", column = @Column(name = "TEMPLATE_ID", nullable = false)),
			@AttributeOverride(name = "accountSchemaId", column = @Column(name = "ACCOUNT_SCHEMA_ID", nullable = false)) })
	private AccountTxTemplateItemId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_SCHEMA_ID", nullable = false, insertable = false, updatable = false)
	private AccountSchema accountSchema;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEMPLATE_ID", nullable = false, insertable = false, updatable = false)
	private AccountTransactionTemplate accountTransactionTemplate;

	@Column(name = "DEBIT_CREDIT", nullable = false, length = 1)
	private String debitCredit;

	@Column(name = "OBJECT_TYPE", length = 2)
	private String objectType;
}
