package com.nauticana.finance.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = BankBranch.TABLE_NAME)
public class BankBranch implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ROOTMAPPING = "bankBranch";
	public static final String TABLE_NAME = "BANK_BRANCH";

	@Id
	@Column(name = "SWIFT", unique = true, nullable = false, length = 11)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BANK_ID", nullable = false)
	private Bank bank;

	@Column(name = "BRANCH_CODE", nullable = false)
	private short branchCode;

	@Column(name = "LATITUDE", precision = 25, scale = 10)
	private BigDecimal latitude;

	@Column(name = "LONGITUDE", precision = 25, scale = 10)
	private BigDecimal longitude;

	@Column(name = "ALTITUDE", precision = 25, scale = 20)
	private BigDecimal altitude;

	@Column(name = "STATE", length = 2)
	private String state;

	@Column(name = "CITY", length = 40)
	private String city;

	@Column(name = "ADDRESS", length = 80)
	private String address;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bankBranch")
	private Set<BankAccount> bankAccounts = new HashSet<BankAccount>(0);
}
