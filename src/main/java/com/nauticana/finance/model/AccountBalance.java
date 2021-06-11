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
@Table(name = "ACCOUNT_BALANCE")
public class AccountBalance implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "accountSchemaId", column = @Column(name = "ACCOUNT_SCHEMA_ID", nullable = false)),
			@AttributeOverride(name = "currency", column = @Column(name = "CURRENCY", nullable = false, length = 3)),
			@AttributeOverride(name = "refDate", column = @Column(name = "REF_DATE", nullable = false, length = 26)) })
	private AccountBalanceId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_SCHEMA_ID", nullable = false, insertable = false, updatable = false)
	private AccountSchema accountSchema;

	@Column(name = "REF_YEAR", nullable = false)
	private short refYear;

	@Column(name = "REF_QUARTER", nullable = false)
	private short refQuarter;

	@Column(name = "REF_MONTH", nullable = false)
	private short refMonth;

	@Column(name = "REF_DAY", nullable = false)
	private short refDay;

	@Column(name = "REF_WEEK", nullable = false)
	private short refWeek;

	@Column(name = "DEBIT", nullable = false, precision = 12)
	private BigDecimal debit;

	@Column(name = "CREDIT", precision = 12)
	private BigDecimal credit;
}
