package com.nauticana.finance.model;

import java.math.BigDecimal;

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
@Table(name = "ACCOUNT_TRANSACTION_ITEM")
public class AccountTransactionItem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "transactionId", column = @Column(name = "TRANSACTION_ID", nullable = false)),
			@AttributeOverride(name = "accountSchemaId", column = @Column(name = "ACCOUNT_SCHEMA_ID", nullable = false)) })
	private AccountTransactionItemId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_SCHEMA_ID", nullable = false, insertable = false, updatable = false)
	private AccountSchema accountSchema;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRANSACTION_ID", nullable = false, insertable = false, updatable = false)
	private AccountTransaction accountTransaction;

	@Column(name = "DEBIT", nullable = false, precision = 12)
	private BigDecimal debit;

	@Column(name = "CREDIT", nullable = false, precision = 12)
	private BigDecimal credit;

	@Column(name = "OBJECT_TYPE", nullable = false, length = 2)
	private String objectType;

	@Column(name = "OBJECT_ID", nullable = false)
	private int objectId;
}
