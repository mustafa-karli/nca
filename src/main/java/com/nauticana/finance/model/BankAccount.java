package com.nauticana.finance.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = BankAccount.TABLE_NAME)
public class BankAccount implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ROOTMAPPING = "bankAccount";
	public static final String TABLE_NAME = "BANK_ACCOUNT";

	@Id
	@Column(name = "IBAN", unique = true, nullable = false, length = 22)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SWIFT", nullable = false)
	private BankBranch bankBranch;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "CURRENCY", nullable = false, length = 3)
	private String currency;

	@Column(name = "ACCOUNT_NO", length = 40)
	private String accountNo;

	@Column(name = "ACCOUNT_TYPE", length = 1)
	private Character accountType;

	@Column(name = "BALANCE", precision = 12)
	private BigDecimal balance;

	@Column(name = "CREDIT_LIMIT", precision = 12)
	private BigDecimal creditLimit;
}
