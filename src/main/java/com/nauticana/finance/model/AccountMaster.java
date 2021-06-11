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
@Table(name = AccountMaster.TABLE_NAME)
public class AccountMaster implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ROOTMAPPING = "accountMaster";
	public static final String TABLE_NAME = "ACCOUNT_MASTER";

	@Id
	@Column(name = "MASTER_ACCOUNT_CODE", unique = true, nullable = false, length = 3)
	private String id;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String caption;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountMaster")
	private Set<AccountSchema> accountSchemas = new HashSet<AccountSchema>(0);
}
