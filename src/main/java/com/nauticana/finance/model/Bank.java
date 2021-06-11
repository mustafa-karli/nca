package com.nauticana.finance.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = Bank.TABLE_NAME)
public class Bank implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ROOTMAPPING = "bank";
	public static final String TABLE_NAME = "BANK";

	@Id
	@Column(name = "BANK_ID", unique = true, nullable = false, length = 8)
	private String id;

	@Column(name = "COUNTRY", nullable = false, length = 2)
	private String country;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String caption;

	@Column(name = "ROUTING", length = 20)
	private String routing;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bank")
	private Set<BankBranch> bankBranches = new HashSet<BankBranch>(0);
}
